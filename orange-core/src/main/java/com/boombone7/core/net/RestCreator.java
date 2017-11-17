package com.boombone7.core.net;

import com.boombone7.core.I;
import com.boombone7.core.app.Orange;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author Ting
 * @date 2017/11/15
 */

public class RestCreator {
    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Orange.getConfiguration(I.Configkey.APPLICATION_HOST);
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkHttpHolder.OKHTTP_CLIENT)
                .build();
    }

    /**
     * 构建OkHttp
     */
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class ServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }

    public static RestService getRestService() {
        return ServiceHolder.REST_SERVICE;
    }


}
