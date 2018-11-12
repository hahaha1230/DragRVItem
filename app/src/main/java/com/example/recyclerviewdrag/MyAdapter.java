package com.example.recyclerviewdrag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 佳佳 on 2018/11/11.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
        implements ItemTouchHelperAdapter {
    private List<String>mData=new ArrayList<>();
    private Context context;

    public  MyAdapter(Context context,List<String> data){
        this.context=context;
        this.mData=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       MyViewHolder holder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.
               item,parent,false));
       return  holder;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /**
         * 加载到最后一个item时候会出现异常，这里catch一下
         */
        try {
            holder.textView.setText(mData.get(position));
        }
        catch (Exception e){
            Log.d("hhh","加载到最后一个时候出现异常");
            e.printStackTrace();
        }

    }

    @Override
    public void onItemMove(RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {
        int fromPosition=source.getAdapterPosition();
        int toPosition=target.getAdapterPosition();
        if (fromPosition<mData.size()&&toPosition<mData.size()){
            //交换数据位置
            Collections.swap(mData,fromPosition,toPosition);
            //刷新位置交换
            notifyItemMoved(fromPosition,toPosition);
        }
        //移动过程中移除view的放大效果
        onItemClear(source);
    }


    @Override
    public void onItemDissmiss(RecyclerView.ViewHolder source) {
        int position=source.getAdapterPosition();
        //移除数据
        mData.remove(position);
        //刷新数据移除
        notifyItemRemoved(position);
    }

    @Override
    public void onItemSelect(RecyclerView.ViewHolder source) {

        //当拖拽选中时放大选中的view
        source.itemView.setScaleX(1.2f);
        source.itemView.setScaleY(1.2f);
    }

    @Override
    public void onItemClear(RecyclerView.ViewHolder source) {

        //拖拽结束后恢复view状态
        source.itemView.setScaleX(1.0f);
        source.itemView.setScaleY(1.0f);

    }

    @Override
    public int getItemCount() {
        //这里最少要有一个，因为有多了一个添加按钮
       return null==mData ? 1 : mData.size()+1;
    }


    public class  MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView;

        public  MyViewHolder(View itemView){
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.textView);
        }
    }
}
