package com.example.recyclerviewdrag;

import android.support.v7.widget.RecyclerView;

/**
 * Created by 佳佳 on 2018/11/11.
 */

public interface ItemTouchHelperAdapter {


    //数据交换
    void onItemMove(RecyclerView.ViewHolder source,RecyclerView.ViewHolder target);

    //数据删除
    void onItemDissmiss(RecyclerView.ViewHolder source);

    //drag或者swipe选中
    void onItemSelect(RecyclerView.ViewHolder source);

    //状态清除
    void onItemClear(RecyclerView.ViewHolder source);
}
