package com.cosplay.wang.commonapp.utils.okhttp;

/**
 * Created by zhiwei.wang on 2017/6/28.
 */

public class OkhttpHeader {
    String name;
    String value;

    public OkhttpHeader(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
