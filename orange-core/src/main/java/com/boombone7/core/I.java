package com.boombone7.core;

/**
 * @author Ting
 * @date 2017/11/10
 */

public interface I {
    interface Configkey {
        String CONFIG_READY = "CONFIG_READY";
        String APPLICATION_CONTEXT = "APPLICATION_CONTEXT";
        String APPLICATION_HOST = "APPLICATION_HOST";
    }

    interface HttpMethod {
        String GET = "GET";
        String POST = "POST";
        String POST_RAW = "POST_RAW";
        String PUT = "PUT";
        String PUT_RAW = "PUT_RAW";
        String DELETE = "DELETE";
        String UPLOAD = "UPLOAD";
    }
}
