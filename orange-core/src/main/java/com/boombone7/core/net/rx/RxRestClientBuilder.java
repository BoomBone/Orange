package com.boombone7.core.net.rx;

import android.content.Context;

import com.boombone7.core.I;
import com.boombone7.core.net.RestClient;
import com.boombone7.core.net.RestCreator;
import com.boombone7.core.net.callback.IError;
import com.boombone7.core.net.callback.IFailure;
import com.boombone7.core.net.callback.IRequest;
import com.boombone7.core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author Ting
 * @date 2017/11/17
 */

public class RxRestClientBuilder {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private RequestBody mBody = null;
    private String mStyle = null;
    private Context mContext = null;
    private File mFile = null;


    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, String style) {
        this.mContext = context;
        this.mStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mStyle = I.LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mUrl, PARAMS, mBody, mContext
                , mStyle, mFile);
    }
}
