package com.cham.helx.test;


import javax.inject.Inject;

/**
 * Hello World
 * Date: 2019/9/12
 * Author: Cham
 */
public class StuA implements StuB {

    private String msg ="Stu";

    @Inject
    public StuA(String s ){
        msg =s;
    }


    public String getMsg(){
        return msg;
    }
}
