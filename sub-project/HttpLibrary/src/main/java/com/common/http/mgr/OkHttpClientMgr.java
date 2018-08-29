package com.common.http.mgr;


import android.os.Build;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.common.http.interceptor.HeadersInterceptor;
import com.common.http.interceptor.HttpLoggingInterceptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by jzy on 2016/10/18.
 */

public class OkHttpClientMgr {
    private final String TAG = "OkHttpClientMgr";

    private static final long FILE_READ_TIME_MIN = 5L;
    private static final long FILE_WRITE_TIME_MIN = 5L;
    private static final long DEFAULT_CONNECT_TIME_SEC = 10L;
    private static final long FILE_CONNECT_TIME_SEC = 100L;

    private static class Inner {
        private static final OkHttpClientMgr mIns = new OkHttpClientMgr();
    }

    private SparseArray<OkHttpClient> mClients = new SparseArray<>(CLIENT_COUNT);

    private OkHttpClientMgr() {
    }

    public static OkHttpClientMgr getIns() {
        return Inner.mIns;
    }

    public
    @NonNull
    OkHttpClient getClient() {
        OkHttpClient client = mClients.get(CLIENT_DEFAULT);
        if (client == null) {
            initClient(CLIENT_DEFAULT, null);
        }
        return mClients.get(CLIENT_DEFAULT);
    }

    public void initClient(@Client int client, ClientBuilder builder) {
        OkHttpClient.Builder defaultBuilder = null;
        if (client == CLIENT_DEFAULT) {
            defaultBuilder = new OkHttpClient.Builder().connectTimeout(DEFAULT_CONNECT_TIME_SEC, TimeUnit.SECONDS);
        } else if (client == CLIENT_FILE) {
            defaultBuilder = getClient().newBuilder().connectTimeout(FILE_CONNECT_TIME_SEC, TimeUnit.SECONDS).writeTimeout(FILE_WRITE_TIME_MIN, TimeUnit.MINUTES).readTimeout(FILE_READ_TIME_MIN, TimeUnit.MINUTES);
        } else {
            defaultBuilder = getClient().newBuilder();
        }

//        defaultBuilder.sslSocketFactory(SSLSocketClient.getInstance().getSslSocketFactory(), SSLSocketClient.getInstance().getTrustManager());

        OkHttpClient httpClient = createClient(defaultBuilder, builder);
        mClients.put(client, httpClient);
    }

    private Map<String, String> getCommonHeader() {
        Map<String, String> headers = new HashMap<>();
        headers.put("versionCode", "1");
        headers.put("version", "1");
        headers.put("channel", "app");
        headers.put("imei", "1");
        headers.put("platform", "2");
        headers.put("token", "");
        headers.put("androidId", "1");
        headers.put("mac", "1");
        headers.put("model", Build.MODEL);
        headers.put("vendor", Build.BRAND);
        headers.put("screenWidth", "1080");
        headers.put("screenHeight", "1920");
        headers.put("operatorType", "1");
        headers.put("connectionType", "1");
        headers.put("deviceType", "1");
        headers.put("ipv4", "192.168.1.1");
        headers.put("osVersion", Build.VERSION.RELEASE);
        headers.put("appPackage", "com.test");
        headers.put("appName", "test");
        return headers;
    }

    private OkHttpClient createClient(OkHttpClient.Builder builder, ClientBuilder clientBuilder) {
        if (clientBuilder != null) {
            clientBuilder.buildClient(builder);
        }
        builder.addInterceptor(new HeadersInterceptor(getCommonHeader()));
        builder.addInterceptor(new HttpLoggingInterceptor(TAG));
        return builder.build();
    }

    public
    @NonNull
    OkHttpClient getClient(@Client int client) {
        OkHttpClient httpClient = mClients.get(client);
        if (httpClient == null) {
            initClient(client, null);
            httpClient = mClients.get(client);
            if (httpClient == null) {
                return getClient();
            }
        }
        return httpClient;
    }

    private static final int CLIENT_COUNT = 4;
    public static final int CLIENT_DEFAULT = 0;
    public static final int CLIENT_API = 1;
    public static final int CLIENT_IMAGE_REQUEST = 2;
    public static final int CLIENT_FILE = 3;

    @IntDef({CLIENT_DEFAULT, CLIENT_API, CLIENT_IMAGE_REQUEST, CLIENT_FILE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Client {
    }

    public interface ClientBuilder {
        void buildClient(OkHttpClient.Builder builder);
    }

}
