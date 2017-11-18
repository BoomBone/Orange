package com.boombone7.core.net.callback;

import android.os.Handler;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;
import com.boombone7.core.net.RestCreator;
import com.boombone7.core.ui.OrangeLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Ting
 * @date 2017/11/17.
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String STYLE;
    private static final Handler HANDLER = Orange.getHandler();

    public RequestCallbacks(IRequest request, ISuccess success, IFailure failure, IError error, String style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.STYLE = style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (ERROR != null) {
                ERROR.onError(response.code(), response.message());
            }
        }

        onRequestFinish();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (FAILURE != null) {
            FAILURE.onFailure();
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        onRequestFinish();
    }

    private void onRequestFinish() {
        if (STYLE != null) {
            long delayed;
            if (Orange.getConfiguration(I.Configkey.LOADER_DELAYED) == null) {
                delayed = 0L;
            } else {
                delayed = Orange.getConfiguration(I.Configkey.LOADER_DELAYED);
            }
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    RestCreator.getParams().clear();
                    OrangeLoader.stopLoading();
                }
            }, delayed);
        }
    }
}
