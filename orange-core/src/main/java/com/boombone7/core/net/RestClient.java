package com.boombone7.core.net;

import com.boombone7.core.I;
import com.boombone7.core.net.callback.IError;
import com.boombone7.core.net.callback.IFailure;
import com.boombone7.core.net.callback.IRequest;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.net.callback.RequestCallbacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author Ting
 * @date 2017/11/15
 */

public class RestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest irequest,
                      ISuccess isuccess,
                      IError ierror,
                      IFailure ifailure,
                      RequestBody body) {
        this.URL = url;
        this.REQUEST = irequest;
        this.SUCCESS = isuccess;
        this.FAILURE = ifailure;
        this.ERROR = ierror;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(String method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }
        switch (method) {
            case I.HttpMethod.GET:
                call = service.get(URL, PARAMS);
                break;
            case I.HttpMethod.POST:
                call = service.post(URL, PARAMS);
                break;
            case I.HttpMethod.PUT:
                call = service.put(URL, PARAMS);
                break;
            case I.HttpMethod.DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    public final void get() {
        request(I.HttpMethod.GET);
    }

    public final void put() {
        request(I.HttpMethod.PUT);
    }

    public final void post() {
        request(I.HttpMethod.POST);
    }

    public final void delete() {
        request(I.HttpMethod.DELETE);
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR);
    }


}
