package com.cham.helx.mvp.entity;

import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.core.app.RemoteInput;

import com.stx.xhb.androidx.entity.SimpleBannerInfo;

import javax.annotation.Resource;
import javax.annotation.Resources;

/**
 * Hello World
 * Date: 2019/9/21
 * Author: Cham
 *
 *
 * 轮播图用  必须继承 SimpleBannerInfo
 */
public class BannerBean extends SimpleBannerInfo {


    public int getId() {
        return id;
    }

    private int id;


    public BannerBean(@DrawableRes int id) {
        this.id = id;
    }

    @Override
    public Object getXBannerUrl() {
        return id;
    }
}
