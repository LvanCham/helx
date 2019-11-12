package com.cham.helx.mvvm.test;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.List;

/**
 * Hello World
 * Date: 2019/10/17
 * Author: Cham
 */
public class Test  extends BaseObservable {


    @Bindable
    public String getS1() {
        return s1;
    }
    @Bindable
    public void setS1(String s1) {
        this.s1 = s1;
    }
    private String s1 ="DataBing";

    public List<String> getmTlist() {
        return mTlist;
    }

    public void setmTlist(List<String> mTlist) {
        this.mTlist = mTlist;
    }

    private List<String> mTlist;





}
