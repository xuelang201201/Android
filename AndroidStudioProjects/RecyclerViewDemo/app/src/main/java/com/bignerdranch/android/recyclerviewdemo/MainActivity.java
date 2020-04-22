package com.bignerdranch.android.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerAdapter<String> mTextAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mTextAdapter = new RecyclerAdapter<String>(this, getData(), R.layout.recycler_item) {
            @Override
            public void convert(RecyclerHolder holder, String data, int position) {
                holder.setText(R.id.text_view, data);
                holder.setImageResource(R.id.image_view, R.mipmap.ic_launcher);
            }
        });
        mTextAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                position = position + 1;
                toastShow("点击" + position);
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                position = position + 1;
                toastShow("长按" + position);
            }
        });

    }

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("数据项目" + "<" + (i + 1) + ">");
        }
        return list;
    }

    Toast toast;

    public void toastShow(String text) {
        if (toast == null) {
            toast = Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public  <T extends View> T findView(int viewId) {
        return (T) findViewById(viewId);
    }
}
