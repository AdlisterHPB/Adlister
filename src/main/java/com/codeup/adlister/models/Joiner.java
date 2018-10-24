package com.codeup.adlister.models;

public class Joiner {
    private long ad_id;
    private long category_id;
    public Joiner(long ad_id, long category_id) {
        this.ad_id = ad_id;
        this.category_id = category_id;
    }

    public long getAd_id() {
        return ad_id;
    }

    public void setAd_id(long ad_id) {
        this.ad_id = ad_id;
    }

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }
}
