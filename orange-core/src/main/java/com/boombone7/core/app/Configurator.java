package com.boombone7.core.app;

import java.util.HashMap;

/**
 * @author Ting
 * @date 2017/11/10
 */

public class Configurator {
    private static final HashMap<String, Object> ORANGE_CONFIGS = new HashMap<>();

    private Configurator() {
        ORANGE_CONFIGS.put(Configkey.CONFIG_READY, false);
    }

    private static class LazyHolder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return LazyHolder.INSTANCE;
    }
    public final void configure(){
        ORANGE_CONFIGS.put(Configkey.CONFIG_READY, true);
    }

    final HashMap<String,Object> getConfig(){
        return ORANGE_CONFIGS;
    }

    public final Configurator withApplication(String applicationHost){
        ORANGE_CONFIGS.put(Configkey.APPLICATION_HOST, applicationHost);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) ORANGE_CONFIGS.get(Configkey.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfiguration(String key){
        checkConfiguration();
        return (T) ORANGE_CONFIGS.get(key);
    }
}
