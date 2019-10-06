package com.cham.helx.base;

/**
 * Hello World
 * Date: 2019/8/31
 * Author: Cham
 */
public interface IView {
    /**
     * 显示加载
     */
    default void showLoading() {

    }

    /**
     * 隐藏加载
     */
    default void hideLoading() {

    }
}
