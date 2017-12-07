package com.boombone7.core.net.rx;

import android.content.Context;

import com.boombone7.core.I;
import com.boombone7.core.net.RestClientBuilder;
import com.boombone7.core.net.RestCreator;
import com.boombone7.core.net.RestService;
import com.boombone7.core.net.callback.IError;
import com.boombone7.core.net.callback.IFailure;
import com.boombone7.core.net.callback.IRequest;
import com.boombone7.core.net.callback.ISuccess;
import com.boombone7.core.net.callback.RequestCallbacks;
import com.boombone7.core.net.download.DownloadHandler;
import com.boombone7.core.ui.OrangeLoader;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * @author Ting
 * @date 2017/11/15
 */

public class RxRestClient {
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final String URL;
    private final RequestBody BODY;
    private final String STYLE;
    private final Context CONTEXT;
    private final File FILE;

    public RxRestClient(String url,
                        WeakHashMap<String, Object> params,
                        RequestBody body,
                        Context context,
                        String style,
                        File file) {
        PARAMS.putAll(params);
        this.URL = url;
        this.BODY = body;
        this.STYLE = style;
        this.CONTEXT = context;
        this.FILE = file;
    }

    public static RxRestClientBuilder builder() {
        return new RxRestClientBuilder();
    }

    private Observable<String> request(String method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (STYLE != null) {
            OrangeLoader.showLoading(CONTEXT, STYLE);
        }
        switch (method) {
            case I.HttpMethod.GET:
                observable = service.get(URL, PARAMS);
                break;
            case I.HttpMethod.POST:
                observable = service.post(URL, PARAMS);
                break;
            case I.HttpMethod.PUT:
                observable = service.put(URL, PARAMS);
                break;
            case I.HttpMethod.DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case I.HttpMethod.POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case I.HttpMethod.PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case I.HttpMethod.UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }
        return observable;
    }

    public final Observable<String> get() {
        return request(I.HttpMethod.GET);
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(I.HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(I.HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(I.HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(I.HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(I.HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(I.HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        final Observable<ResponseBody> observable = RestCreator.getRxRestService().download(URL, PARAMS);
        return observable;
    }
}
