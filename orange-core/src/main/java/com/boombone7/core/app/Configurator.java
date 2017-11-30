package com.boombone7.core.app;

import android.os.Handler;

import com.boombone7.core.I;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * @author Ting
 * @date 2017/11/10
 */

public class Configurator {
    private static final HashMap<String, Object> ORANGE_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        ORANGE_CONFIGS.put(I.Configkey.CONFIG_READY, false);
        ORANGE_CONFIGS.put(I.Configkey.HANDLER, HANDLER);
    }

    private static class LazyHolder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return LazyHolder.INSTANCE;
    }
    public final void configure(){
        ORANGE_CONFIGS.put(I.Configkey.CONFIG_READY, true);
    }

    final HashMap<String,Object> getConfig(){
        return ORANGE_CONFIGS;
    }

    public final Configurator withApiHost(String apiHost){
        ORANGE_CONFIGS.put(I.Configkey.API_HOST, apiHost);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        ORANGE_CONFIGS.put(I.Configkey.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        ORANGE_CONFIGS.put(I.Configkey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        ORANGE_CONFIGS.put(I.Configkey.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) ORANGE_CONFIGS.get(I.Configkey.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final  <T> T getConfiguration(String key){
        checkConfiguration();
        return (T) ORANGE_CONFIGS.get(key);
    }
}
