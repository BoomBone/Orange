package com.boombone7.core.net;

import com.boombone7.core.net.callback.IError;
import com.boombone7.core.net.callback.IFailure;
import com.boombone7.core.net.callback.IRequest;
import com.boombone7.core.net.callback.ISuccess;

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

    public final RestClient build(){
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIError, mIFailure, mBody);
    }
}
