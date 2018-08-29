package com.library.db.dao;

import android.content.Context;

import com.library.db.bean.DBCacheBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jzy on 2017/6/16.
 */

public class DBCacheDao extends DBBaseDao<DBCacheBean> {
    public static final long CACHE_DATE_HOUR = 60 * 60 * 1000;


    public DBCacheDao(Context context) {
        super(context, new DBCacheBean());
    }

    /**
     * 获取缓存
     *
     * @param id
     * @param type
     * @return
     */
    public DBCacheBean getCache(String id, int type) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("cache_id", id);
        List<DBCacheBean> list = getByParams(params, null, true);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    /**
     * 添加缓存
     *
     * @param id
     * @param type
     * @param json
     */
    public void saveCache(String id, int type, String json) {
        // 删除缓存
        delCache(id, type);
        // 添加缓存
        DBCacheBean cacheBean = new DBCacheBean();
        cacheBean.setType(type);
        cacheBean.setCache_id(id);
        cacheBean.setJson(json);
        add(cacheBean);
    }

    public void delCache(String id, int type) {
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("cache_id", id);
        deleteByParams(params);
    }
}
