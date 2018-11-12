package com.example.recyclerviewdrag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String>mDatas=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //创建adapter
        MyAdapter adapter=new MyAdapter(this,mDatas);
        //设置默认的布局方式
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false));
        //设置adapter
        recyclerView.setAdapter(adapter);

        //创建simpleItemTouchHelper
        ItemTouchHelper.Callback callback=new SimpleItemTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper=new ItemTouchHelper(callback);
        //调用itemtouchhelper的attachToRecyclerview方法建立联系
        touchHelper.attachToRecyclerView(recyclerView);
    }

    private void initDatas() {
        for (int i=0;i<30;i++){
            mDatas.add("哈哈哈"+i);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
