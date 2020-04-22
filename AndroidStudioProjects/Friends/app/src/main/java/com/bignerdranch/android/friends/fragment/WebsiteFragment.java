package com.bignerdranch.android.friends.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.bignerdranch.android.friends.R;
import com.bignerdranch.android.friends.constant.Constant.Position;

/**
 * 网页浏览fragment
 */
public class WebsiteFragment extends BaseFragment {
    private static final String ARG_URI = "website_url";

    private Uri mUri;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private View headerView;

    public static WebsiteFragment newInstance(Uri uri) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_URI, uri);

        WebsiteFragment fragment = new WebsiteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUri = getArguments().getParcelable(ARG_URI);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_website, container, false);
        headerView = v.findViewById(R.id.headerview);
        // 初始化ActionBar
        initHeaderView();

        mProgressBar = (ProgressBar) v.findViewById(R.id.fragment_website_progress_bar);
        mProgressBar.setMax(100); // 进度条范围0-100

        mWebView = (WebView) v.findViewById(R.id.fragment_website_web_view);
        // 加载URL
        mWebView.getSettings().setJavaScriptEnabled(true); // 启用JavaScript
        mWebView.setWebChromeClient(new WebChromeClient() {
            /**
             * 更新进度条
             * @param view WebView
             * @param newProgress 改变中的进度
             */
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (!url.startsWith("http")) {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                    return true;
                } else {
                    return false;
                }
            }
        });
        loadWebsite(mUri.toString());

        return v;
    }

    private void loadWebsite(String website) {
        // webView.loadUrl("http://www.baidu.com");
        if (website.startsWith("http")) {
            mWebView.loadUrl(mUri.toString());
        } else {
            mWebView.loadUrl("http://" + mUri.toString());
        }
        mProgressBar.setVisibility(View.GONE);
    }

    private void initHeaderView() {
        setHeaderImage(headerView, R.drawable.ic_back, Position.LEFT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        setHeaderImage(headerView, R.drawable.ic_reload, Position.RIGHT, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadWebsite(mUri.toString());
            }
        });
        final String title;
        if (mUri.toString().startsWith("http")) {
            title = mUri.toString();
        } else {
            title = "http://" + mUri.toString();
        }
        setHeaderTitle(headerView, title, Position.LEFT);
    }

    public boolean onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            return false;
        }
    }
}
