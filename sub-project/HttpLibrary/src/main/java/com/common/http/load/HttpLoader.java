package com.common.http.load;

import com.common.http.common.BaseCommonRemoteLoader;
import com.common.http.common.ICommonSource;

/**
 * @author jzy
 * created at 2018/5/28
 */
public class HttpLoader extends BaseCommonRemoteLoader {


    private HttpLoader() {
    }

    private static class HOLDER {
        static final HttpLoader INS = new HttpLoader();
    }

    public static ICommonSource getInstance() {
        return HOLDER.INS;
    }
}
