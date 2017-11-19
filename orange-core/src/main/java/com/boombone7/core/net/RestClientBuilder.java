package com.boombone7.core.net;

import android.content.Context;

import com.boombone7.core.I;
import com.boombone7.core.net.callback.IError;
import com.boombone7.core.net.callback.IFailure;
import com.boombone7.core.net.callback.IRequest;
import com.boombone7.core.net.callback.ISuccess;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 *
 * @author Ting
 * @date 2017/11/17
 */

public class RestClientBuilder  {

    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IError mIError = null;
    private IFailure mIFailure = null;
    private RequestBody mBody = null;
    private String mStyle = null;
    private Context mContext = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;


    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }
    public final RestClientBuilder params(String key,Object value){
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest irequest){
        this.mIRequest = irequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess isuccess){
        this.mISuccess = isuccess;
        return this;
    }

    public final RestClientBuilder error(IError ierror){
        this.mIError = ierror;
        return this;
    }

    public final RestClientBuilder failure(IFailure ifailure){
        this.mIFailure = ifailure;
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder loader(Context context, String style) {
        this.mContext = context;
        this.mStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mStyle = I.LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess
                , mIError, mIFailure, mBody, mContext
                ,mStyle,mFile,mDownloadDir,mExtension,mName);
    }
}
