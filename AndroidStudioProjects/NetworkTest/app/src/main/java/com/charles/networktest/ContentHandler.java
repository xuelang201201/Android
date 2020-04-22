package com.charles.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {

    private static final String TAG = "ContentHandler";

    private String mNodeName;
    private StringBuilder mId, mName, mVersion;

    @Override
    public void startDocument() throws SAXException {
        mId = new StringBuilder();
        mName = new StringBuilder();
        mVersion = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // 记录当前结点名
        mNodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // 根据当前的结点名判断将内容添加到哪一个StringBuilder对象中
        if ("id".equals(mNodeName)) {
            mId.append(ch, start, length);
        } else if ("name".equals(mNodeName)) {
            mName.append(ch, start, length);
        } else if ("version".equals(mNodeName)) {
            mVersion.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)) {
            Log.d(TAG, "id: " + mId.toString().trim());
            Log.d(TAG, "name: " + mName.toString().trim());
            Log.d(TAG, "version: " + mVersion.toString().trim());
            // 最后要将StringBuilder清空掉
            mId.setLength(0);
            mName.setLength(0);
            mVersion.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
    }
}
