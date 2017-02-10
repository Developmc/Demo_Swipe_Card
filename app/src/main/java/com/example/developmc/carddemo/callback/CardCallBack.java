package com.example.developmc.carddemo.callback;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.developmc.carddemo.CardConfig;

import java.util.List;

/**用于ItemTouchHelper的回调
 * Created by clement on 17/2/10.
 */

public class CardCallBack extends ItemTouchHelper.SimpleCallback{
    private List<Integer> datas;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    /**
     * @param datas           数据源
     * @param recyclerView    recyclerView对象
     * @param adapter         recyclerView的adapter
     * @param dragDirs
     * @param swipeDirs
     */
    public CardCallBack(List<Integer> datas,RecyclerView recyclerView,RecyclerView.Adapter adapter,int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
        this.datas = datas;
        this.recyclerView = recyclerView;
        this.adapter = adapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //实现循环的要点
        Integer remove = datas.remove(viewHolder.getLayoutPosition());
        datas.add(0, remove);
        adapter.notifyDataSetChanged();
    }

    /**滑动时动画
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //先根据滑动的dxdy 算出现在动画的比例系数fraction
        double swipeValue = Math.sqrt(dX * dX + dY * dY);
        double fraction = swipeValue / getThreshold(viewHolder);
        //边界修正 最大为1
        if (fraction > 1) {
            fraction = 1;
        }
        //对每个ChildView进行缩放 位移
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = recyclerView.getChildAt(i);
            //第几层,举例子，count =7， 最后一个TopView（6）是第0层，
            int level = childCount - i - 1;
            if (level > 0) {
                child.setScaleX((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));

                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    child.setScaleY((float) (1 - CardConfig.SCALE_GAP * level + fraction * CardConfig.SCALE_GAP));
                    child.setTranslationY((float) (CardConfig.TRANS_Y_GAP * level - fraction * CardConfig.TRANS_Y_GAP));
                }
            }
        }
    }
    //水平方向是否可以被回收掉的阈值
    public float getThreshold(RecyclerView.ViewHolder viewHolder) {
        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
    }
}
