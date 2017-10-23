package com.harry.unirecruit.dao;

/**
 * Created by 毕空 on 2017/10/23.
 */

public class UniData {
    private long cityId;
    private String cityName;
    private String uniName;//名称
    private String uniDesc;//描述
    private String uniUrl;//链接
    private String uniCategory;//分类

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getUniDesc() {
        return uniDesc;
    }

    public void setUniDesc(String uniDesc) {
        this.uniDesc = uniDesc;
    }

    public String getUniUrl() {
        return uniUrl;
    }

    public void setUniUrl(String uniUrl) {
        this.uniUrl = uniUrl;
    }

    public String getUniCategory() {
        return uniCategory;
    }

    public void setUniCategory(String uniCategory) {
        this.uniCategory = uniCategory;
    }
}
