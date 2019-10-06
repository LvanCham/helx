package com.cham.helx.mvp.entity;

/**
 * Hello World
 * Date: 2019/9/26
 * Author: Cham
 */
public class PoetryEntity {


    /**
     * content : 溪云初起日沉阁，山雨欲来风满楼。
     * origin : 咸阳城东楼 / 咸阳城西楼晚眺 / 西门
     * author : 许浑
     * category : 古诗文-天气-写风
     */

    private String content;
    private String origin;
    private String author;
    private String category;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
