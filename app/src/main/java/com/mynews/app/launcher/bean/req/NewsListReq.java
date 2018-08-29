package com.mynews.app.launcher.bean.req;


import com.common.http.bean.BaseReq;

/**
 * Created by yangmansheng on 2018/3/15.
 */

public class NewsListReq extends BaseReq {

    private String id = "";
    private String raw_id = "";
    private String cat_ids = "1001";
    private int source_id = 2;
    private int page = 1;
    private int page_size = 10;
    private int type = 1;

    @Override
    public String getUrlPath() {
        return "news/list-new";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaw_id() {
        return raw_id;
    }

    public void setRaw_id(String raw_id) {
        this.raw_id = raw_id;
    }

    public String getCat_ids() {
        return cat_ids;
    }

    public void setCat_ids(String cat_ids) {
        this.cat_ids = cat_ids;
    }

    public int getSource_id() {
        return source_id;
    }

    public void setSource_id(int source_id) {
        this.source_id = source_id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
