package com.cham.helx.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Hello World
 * Date: 2019/8/31
 * Author: Cham
 */
public interface IActivity {

    int initView(@Nullable Bundle savedInstanceState);

    void initData();

}
