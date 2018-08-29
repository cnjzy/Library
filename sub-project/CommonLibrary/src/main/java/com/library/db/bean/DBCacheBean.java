package com.library.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by jzy on 2017/6/16.
 */
@DatabaseTable(tableName = "cache")
public class DBCacheBean extends DBBaseBean {

    // 先写这里吧回头挪地方
    public static final int CACHE_TYPE_TOPIC_HEADER = 1;
    public static final int CACHE_TYPE_TOPIC_POST_LIST = 2;
    public static final int CACHE_TYPE_TOPIC_LIST = 3;
    public static final int CACHE_TYPE_RECOMMEND = 4;

    @DatabaseField
    private String cache_id;
    @DatabaseField
    private int type;
    @DatabaseField
    private String url;
    @DatabaseField
    private String json;
    @DatabaseField
    private long date = System.currentTimeMillis();

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

//    public String getCache_id() {
//        return MD5.hexdigest(type + url + json);
//    }

    public void setCache_id(String cache_id) {
        this.cache_id = cache_id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DBCacheBean{" +
                "cache_id='" + cache_id + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", json='" + json + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
