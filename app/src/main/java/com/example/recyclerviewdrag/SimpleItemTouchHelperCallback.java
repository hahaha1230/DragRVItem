package com.example.recyclerviewdrag;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by 佳佳 on 2018/11/11.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback{

    private ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter){
        this.mAdapter=adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //允许上下拖动
        //int dragFlags=ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //允许左右拖动
       // int dragFlags=ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT;
       // int swipeFlags=ItemTouchHelper.LEFT;//只允许从右向左滑动
       // int swipeFlags=ItemTouchHelper.DOWN;//只允许从上向下侧滑
        //一般使用makeMovementFlags（int，int）或makeFlag（int，int）来构造我们的返回值
       // makeMovementFlags(dragFlags,swipeFlags);

        int dragFlags=ItemTouchHelper.UP |ItemTouchHelper.DOWN| ItemTouchHelper.LEFT|
                ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags,0);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true; //长按启动拖动
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;//不启用拖拽删除
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //移动删除回调，如果不用刻意不用理会
       // mAdapter.onItemDissmiss(viewHolder);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
       //通过接口传递拖拽交换数据的起始位置和目标位置的viewholder
        mAdapter.onItemMove(viewHolder,target);
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if (actionState !=ItemTouchHelper.ACTION_STATE_IDLE){
            //当滑动或者拖拽view的时候通过接口返回该viewholder
            mAdapter.onItemSelect(viewHolder);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (!recyclerView.isComputingLayout()){
            //当需要清除之前在onselectedChanged或者onChildDraw，onChildDrawOver设置的状态或者
            //动画时通过接口返回该viewholder
            mAdapter.onItemClear(viewHolder);
        }
    }
}
