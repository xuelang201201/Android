package com.bignerdranch.android.photogallery.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bignerdranch.android.photogallery.R;
import com.bignerdranch.android.photogallery.bean.GalleryItem;
import com.bignerdranch.android.photogallery.service.PollService;
import com.bignerdranch.android.photogallery.ui.PhotoPageActivity;
import com.bignerdranch.android.photogallery.util.FlickrFetchr;
import com.bignerdranch.android.photogallery.util.QueryPreferences;
import com.bignerdranch.android.photogallery.util.ThumbnailDownloader;

import java.util.ArrayList;
import java.util.List;

public class PhotoGalleryFragment extends VisibleFagment {

    private static final String TAG = "PhotoGalleryFragment";

    private RecyclerView mPhotoRecyclerView;
    private List<GalleryItem> mItems = new ArrayList<>(); // 数据源
    private ThumbnailDownloader<PhotoHolder> mThumbnailDownloader;

    private ProgressDialog mProgressDialog;
    private MenuItem mSearchItem;

    public static PhotoGalleryFragment newInstance() {
        return new PhotoGalleryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // 设备旋转时，保留fragment状态
        setHasOptionsMenu(true); // 有选项菜单
        updateItems(); // 启动异步任务，开始网络连接

        /**关联使用反馈Handler*/
        // 主线程Handler
        Handler responseHandler = new Handler();
        // 将关联主线程的Handler传递给ThumbnailDownloader
        mThumbnailDownloader = new ThumbnailDownloader<>(responseHandler);
        mThumbnailDownloader.setThumbnailDownloadListener(
                new ThumbnailDownloader.ThumbnailDownloadListener<PhotoHolder>() { // 处理已下载图片
                    @Override
                    public void onThumbnailDownloaded(PhotoHolder photoHolder, Bitmap bitmap) {
                        Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                        photoHolder.bindDrawable(drawable);
                    }
                }
        );
        mThumbnailDownloader.start(); // 启动线程
        mThumbnailDownloader.getLooper(); // 保证线程就绪，可避免线程竞争
        Log.i(TAG, "Background thread started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, container, false);

        mPhotoRecyclerView = (RecyclerView) v
                .findViewById(R.id.fragment_photo_gallery_recycler_view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        setupAdapter(); // adapter

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mThumbnailDownloader.clearQueue(); // 视图销毁后，清空下载器
        mProgressDialog.dismiss();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mThumbnailDownloader.quit(); // 销毁线程
        Log.i(TAG, "Background thread destroyed");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_photo_gallery, menu);

        mSearchItem = menu.findItem(R.id.menu_item_search);
        final SearchView searchView = (SearchView) mSearchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "QueryTextSubmit: " + s);
                QueryPreferences.setStoredQuery(getActivity(), s); // 存储用户提交的查询信息

                collapseSearchView(); // 折叠SearchView

                updateItems(); // 更新图片
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "QueryTextChange:" + s);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = QueryPreferences.getStoredQuery(getActivity());
                searchView.setQuery(query, false);
            }
        });

        MenuItem toggleItem = menu.findItem(R.id.menu_item_toggle_polling);
        if (PollService.isServiceAlarmOn(getActivity())) {
            toggleItem.setTitle(R.string.stop_polling);
        } else {
            toggleItem.setTitle(R.string.start_polling);
        }
    }

    private void collapseSearchView() {
        mSearchItem.collapseActionView(); // 折叠action view
        View view = getActivity().getCurrentFocus(); // 隐藏键盘
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_clear: // 清除查询信息
                QueryPreferences.setStoredQuery(getActivity(), null);
                updateItems();
                return true;
            case R.id.menu_item_toggle_polling: // 定时器轮询
                boolean shouldStartAlarm = !PollService.isServiceAlarmOn(getActivity());
                PollService.setServiceAlarm(getActivity(), shouldStartAlarm);
                getActivity().invalidateOptionsMenu(); // 让选项菜单失效
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateItems() {
        String query = QueryPreferences.getStoredQuery(getActivity());
        new FetchItemsTask(query).execute(); // 根据不同方式进行操作
    }

    public class PhotoHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ImageView mItemImageView;
        private GalleryItem mGalleryItem;

        public PhotoHolder(View itemView) {
            super(itemView);

            mItemImageView = (ImageView) itemView
                    .findViewById(R.id.fragment_photo_gallery_image_view);
            itemView.setOnClickListener(this);
        }

        public void bindDrawable(Drawable drawable) {
            mItemImageView.setImageDrawable(drawable);
        }

        public void bindGalleryItem(GalleryItem galleryItem) {
            mGalleryItem = galleryItem;
        }

        @Override
        public void onClick(View v) {
            // 通过隐式intent实现网页浏览
            // Intent i = new Intent(Intent.ACTION_VIEW, mGalleryItem.getPhotoPageUri());
            // 启动新建的activity
            Intent i = PhotoPageActivity
                    .newIntent(getActivity(), mGalleryItem.getPhotoPageUri());
            startActivity(i);
        }
    }

    public class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<GalleryItem> mGalleryItems;

        public PhotoAdapter(List<GalleryItem> galleryItems) {
            mGalleryItems = galleryItems;
        }

        @Override
        public PhotoHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.gallery_item, viewGroup, false);
            return new PhotoHolder(view); // 将结果返回给PhotoAdapter的构造方法
        }

        @Override
        public void onBindViewHolder(PhotoHolder photoHolder, int position) {
            GalleryItem galleryItem = mGalleryItems.get(position);
            String url = galleryItem.getUrl();
            photoHolder.bindGalleryItem(galleryItem);
            Bitmap bitmap  = mThumbnailDownloader.getCachedImage(url);

            if (bitmap == null) {
                Drawable placeHolder = getResources().getDrawable(R.drawable.empty_image);
                photoHolder.bindDrawable(placeHolder);
                // 关联使用ThumbnailDownloader
                mThumbnailDownloader.queueThumbnail(photoHolder, url);
            } else {
                Log.i(TAG, "Loaded image from cache");
                photoHolder.bindDrawable(new BitmapDrawable(getResources(), bitmap));
            }
        }

        @Override
        public int getItemCount() {
            return mGalleryItems.size();
        }
    }

    private void setupAdapter() {
        if (isAdded()) {
            mPhotoRecyclerView.setAdapter(new PhotoAdapter(mItems));

            /**
             * 分页显示
             */
            mPhotoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        GridLayoutManager gridLayoutManager =
                                (GridLayoutManager) mPhotoRecyclerView.getLayoutManager();
                        PhotoAdapter photoAdapter = (PhotoAdapter) mPhotoRecyclerView.getAdapter();
                        int startingPos = gridLayoutManager.findLastVisibleItemPosition() + 1;
                        int upperLimit = Math.min(startingPos + 10, photoAdapter.getItemCount());
                        for (int i = startingPos; i < upperLimit; i++) {
                            mThumbnailDownloader.preloadImage(mItems.get(i).getUrl());
                        }

                        startingPos = gridLayoutManager.findFirstVisibleItemPosition() - 1;
                        int lowerLimit = Math.max(startingPos - 10, 0);
                        for (int i = startingPos; i > lowerLimit; i--) {
                            mThumbnailDownloader.preloadImage(mItems.get(i).getUrl());
                        }
                    } else if(newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        mThumbnailDownloader.clearQueue();
                    }
                }
            });
        }
    }

    /**
     * 后台线程，调用网络连接代码
     */
    private class FetchItemsTask extends AsyncTask<Void, Void, List<GalleryItem>> {
        private String mQuery;

        public FetchItemsTask(String query) {
            mQuery = query;
        }

        @Override
        protected List<GalleryItem> doInBackground(Void... params) {

            if (mQuery == null) {
                return new FlickrFetchr().fetchRecentPhotos(); // 下载全部最新图片
            } else {
                return new FlickrFetchr().searchPhotos(mQuery); // 根据查询关键字下载图片
            }
        }

        @Override
        protected void onPreExecute() {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setMessage("正在加载中，请稍后...");
            mProgressDialog.setCancelable(false); // 不可取消
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        }

        /**
         * 该方法接收doInBackground(...)方法返回的GalleryItem数据，并放入mItems变量
         * @param items
         */
        @Override
        protected void onPostExecute(List<GalleryItem> items) {
            mItems = items;
            setupAdapter(); // 更新RecyclerView视图的adapter

            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
    }
}
