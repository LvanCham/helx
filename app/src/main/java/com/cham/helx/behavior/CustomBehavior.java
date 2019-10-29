package com.cham.helx.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Hello World
 * Date: 2019/10/29
 * Author: Cham
 * 自定义学习Behavior
 */
public class CustomBehavior  extends CoordinatorLayout.Behavior<View> {

    private String TAG = "CustomBehavior";
    private float mToolbarHegiht;

    public CustomBehavior(){
        super();
    }

    public  CustomBehavior(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    /**
     * 建立监听控件或容器依赖
     *
     * @param parent     父容器
     * @param child      观察者，监听其他
     * @param dependency 被观察者
     * @return
     */
    //layoutDepentsOn, 至少会调用一次, 用于判断CoordinatorLayout下的dependency是否是我们指定的依赖.


    @Override
    public boolean layoutDependsOn(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {

        if (mToolbarHegiht == 0) {
            mToolbarHegiht = dependency.getHeight();
        }

        return dependency instanceof AppBarLayout;
    }

    /**
     * 被监听的View改变时，相应的处理
     *
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    @Override
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout parent, @NonNull View child, @NonNull View dependency) {
        float fraction = Math.abs(dependency.getTop()) / mToolbarHegiht;

        Log.e(TAG, "onDependentViewChanged: "+ fraction );

        if(fraction>0.7){
            child.setScaleX(0);
            child.setScaleY(0);
        }else {
            child.setScaleX(1 - fraction);
            child.setScaleY(1 - fraction);
        }


        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull View child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                  @NonNull View child,
                                  @NonNull View target,
                                  int dx, int dy,
                                  @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);


    }
}
