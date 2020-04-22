package com.bignerdranch.android.photogallery.util;

import android.net.Uri;
import android.util.Log;

import com.bignerdranch.android.photogallery.bean.GalleryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络连接专用类
 */
public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";

    private static final String API_KEY = "bc512261ae9b721cdae6044bfe03079c";
    private static final String FETCH_RECENTS_METHOD = "flickr.photos.getRecent";
    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final Uri ENDPOINT = Uri
            .parse("https://api.flickr.com/services/rest/")
            .buildUpon()
            .appendQueryParameter("api_key", API_KEY)
            .appendQueryParameter("format", "json")
            .appendQueryParameter("nojsoncallback", "1")
            .appendQueryParameter("extras", "url_s") // 如有小尺寸图片，也一并返回其URL
            .build();

    /**
     * 从指定URL获取原始数据并返回一个字节流数组。
     * @param urlSpec
     * @return
     * @throws IOException
     */
    public byte[] getUrlBytes(String urlSpec) throws IOException {
        // 根据传入的字符串对象参数，如http://www.google.com，创建一个URL对象
        URL url = new URL(urlSpec);
        // 调用openConnection()方法创建一个指向要访问URL的连接对象
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 虽然HttpURLConnection对象提供了一个连接，但只有在调用getInputStream()方法时
            // （如果是POST请求，则调用getOutputStream()方法），它才会真正连接到指定的URL地址。
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            // 创建了URL并打开网络连接之后，便可循环调用read()方法读取网络数据，直到取完为止。
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            // 数据取完后
            out.close(); // 关闭网络连接
            return out.toByteArray(); // 将读取的数据写入ByteArrayOutputStream字节数组中
        } finally {
            connection.disconnect();
        }
    }

    /**
     * 将getUrlBytes(String)方法获取的字节流数据转换为String
     * @param urlSpec
     * @return
     * @throws IOException
     */
    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    /**
     * 下载
     * @return
     */
    public List<GalleryItem> fetchRecentPhotos() {
        String url = buildUrl(FETCH_RECENTS_METHOD, null);
        return downloadGalleryItems(url);
    }

    /**
     * 搜索
     * @param query 查询关键字
     * @return
     */
    public List<GalleryItem> searchPhotos(String query) {
        String url = buildUrl(SEARCH_METHOD, query);
        return downloadGalleryItems(url);
    }

    /**
     * 构建请求URL并获取内容，下载图片
     */
    public List<GalleryItem> downloadGalleryItems(String url) {

        List<GalleryItem> items = new ArrayList<>();

        try {
            String jsonString = getUrlString(url); // 得到JSON字符串
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString); // 把JSON数据解析进相应的Java对象
            parseItems(items, jsonBody); // 调用parseItems(...)方法，解析图片
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    /**
     * 创建URL的辅助方法
     * @param method 判断是下载还是搜索
     * @param query 搜素
     * @return
     */
    private String buildUrl(String method, String query) {
        Uri.Builder uriBuilder = ENDPOINT.buildUpon()
                .appendQueryParameter("method", method);

        if (method.equals(SEARCH_METHOD)) {
            uriBuilder.appendQueryParameter("text", query);
        }

        return uriBuilder.build().toString();
    }

    /**
     * 解析Flickr图片
     * 解析JSONObject层级结构
     * @param items
     * @param jsonBody
     */
    private void parseItems(List<GalleryItem> items, JSONObject jsonBody)
            throws JSONException {

        JSONObject photosJsonObject = jsonBody.getJSONObject("photos");
        JSONArray photoJsonArray = photosJsonObject.getJSONArray("photo");

        for (int i = 0; i < photoJsonArray.length(); i++) {
            JSONObject photoJsonObject = photoJsonArray.getJSONObject(i);

            GalleryItem item = new GalleryItem();
            item.setId(photoJsonObject.getString("id"));
            item.setCaption(photoJsonObject.getString("title"));

            if (!photoJsonObject.has("url_s")) {
                continue;
            }

            item.setUrl(photoJsonObject.getString("url_s"));
            item.setOwner(photoJsonObject.getString("owner")); // 从JSON数据中获取owner属性
            items.add(item);
        }
    }
}
