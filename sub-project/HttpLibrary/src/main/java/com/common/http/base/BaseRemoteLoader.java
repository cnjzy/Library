package com.common.http.base;

//import com.library.data.InterceptLoadDataCallback;

import com.common.http.mgr.OkHttpClientMgr;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.host.HostHelper;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jzy on 2017/5/18.
 */

public class BaseRemoteLoader<T> {
    protected T mServiceApi = null;
    private static final String BASE_URL = HostHelper.getInstance().getHost();

    protected BaseRemoteLoader(Class<T> service) {
        this(service, BASE_URL, OkHttpClientMgr.CLIENT_API);
    }

    protected BaseRemoteLoader(Class<T> service, String url) {
        this(service, url, OkHttpClientMgr.CLIENT_API);
    }

    protected BaseRemoteLoader(Class<T> service, int clientType) {
        this(service, BASE_URL, clientType);
    }

    private BaseRemoteLoader(Class<T> service, String url, int clientType) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(OkHttpClientMgr.getIns().getClient(clientType))
                .build();
        mServiceApi = mRetrofit.create(service);
    }

    protected T getServiceApi() {
        return mServiceApi;
    }
}
