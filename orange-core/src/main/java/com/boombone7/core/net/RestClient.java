package com.boombone7.core.net;

import android.content.Context;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.net.callback.IError;
import com.boombone7.core.net.callback.IFailure;
import com.boombone7.core.net.callback.IRequest;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.net.callback.RequestCallbacks;
import com.boombone7.core.net.download.DownloadHandler;
import com.boombone7.core.ui.OrangeLoader;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final String STYLE;
    private final Context CONTEXT;
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;

    public RestClient(String url,
                      WeakHashMap<String, Object> params,
                      IRequest irequest,
                      ISuccess isuccess,
                      IError ierror,
                      IFailure ifailure,
                      RequestBody body,
                      Context context,
                      String style,
                      File file,
                      String downloadDir,
                      String extension,
                      String name) {
        PARAMS.putAll(params);
        this.URL = url;
        this.REQUEST = irequest;
        this.SUCCESS = isuccess;
        this.FAILURE = ifailure;
        this.ERROR = ierror;
        this.BODY = body;
        this.STYLE = style;
        this.CONTEXT = context;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
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

        if (STYLE != null) {
            OrangeLoader.showLoading(CONTEXT, STYLE);
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
            case I.HttpMethod.POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case I.HttpMethod.PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case I.HttpMethod.UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
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
        if (BODY == null) {
            request(I.HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(I.HttpMethod.PUT_RAW);
        }
    }

    public final void post() {
        if (BODY == null) {
            request(I.HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(I.HttpMethod.POST_RAW);
        }
    }

    public final void delete() {
        request(I.HttpMethod.DELETE);
    }

    public final void upload() {
        request(I.HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME,
                SUCCESS, FAILURE, ERROR)
                .handleDownload();
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, STYLE);
    }


}
