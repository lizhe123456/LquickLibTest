package com.empty.lquicklibtest.bean;

/**
 * Created by lizhe on 2019/4/25.
 *
 */

public class MainBean {

    private String name;
    private String title;
    private double num;


    public MainBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}
