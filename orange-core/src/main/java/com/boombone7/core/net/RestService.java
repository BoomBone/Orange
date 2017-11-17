package com.boombone7.core.net;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Ting on 2017/11/15.
 */

public interface RestService  {
    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);
}
