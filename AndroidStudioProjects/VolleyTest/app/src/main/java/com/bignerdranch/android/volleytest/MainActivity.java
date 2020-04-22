package com.bignerdranch.android.volleytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private NetworkImageView mNetworkImageView;
    private RequestQueue mQueue;
    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = new ImageLoader(mQueue, new BitmapCache());

        showImageView();

        showNetworkImageView();
    }

    private void showImageView() {
        mImageView = (ImageView) findViewById(R.id.image);

        ImageLoader.ImageListener listener = ImageLoader
                .getImageListener(mImageView, R.drawable.default_image, R.drawable.failed_image);

        imageLoader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
    }

    private void showNetworkImageView() {
        mNetworkImageView = (NetworkImageView) findViewById(R.id.network_image_view);
        mNetworkImageView.setDefaultImageResId(R.drawable.default_image);
        mNetworkImageView.setErrorImageResId(R.drawable.failed_image);
        String url = "https://lh4.googleusercontent.com/-JhFi4fb_Pqw/URquuX-QXbI/AAAAAAAAAbs/IXpYUxuweYM/s160-c/Horseshoe%252520Bend.jpg";
        mNetworkImageView.setImageUrl(url, imageLoader);
    }
}
