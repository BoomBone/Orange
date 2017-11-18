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
        String LOADER_DELAYED = "LOADER_DELAYED";
        String HANDLER = "HANDLER";
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

    interface LoaderStyle {
        String BallPulseIndicator = "BallPulseIndicator";
        String BallGridPulseIndicator = "BallGridPulseIndicator";
        String BallClipRotateIndicator = "BallClipRotateIndicator";
        String BallClipRotatePulseIndicator = "BallClipRotatePulseIndicator";
        String SquareSpinIndicator = "SquareSpinIndicator";
        String BallClipRotateMultipleIndicator = "BallClipRotateMultipleIndicator";
        String BallPulseRiseIndicator = "BallPulseRiseIndicator";
        String BallRotateIndicator = "BallRotateIndicator";
        String CubeTransitionIndicator = "CubeTransitionIndicator";
        String BallZigZagIndicator = "BallZigZagIndicator";
        String BallZigZagDeflectIndicator = "BallZigZagDeflectIndicator";
        String BallTrianglePathIndicator = "BallTrianglePathIndicator";
        String BallScaleIndicator = "BallScaleIndicator";
        String LineScaleIndicator = "LineScaleIndicator";
        String LineScalePartyIndicator = "LineScalePartyIndicator";
        String BallScaleMultipleIndicator = "BallScaleMultipleIndicator";
        String BallPulseSyncIndicator = "BallPulseSyncIndicator";
        String BallBeatIndicator = "BallBeatIndicator";
        String LineScalePulseOutIndicator = "LineScalePulseOutIndicator";
        String LineScalePulseOutRapidIndicator = "LineScalePulseOutRapidIndicator";
        String BallScaleRippleIndicator = "BallScaleRippleIndicator";
        String BallScaleRippleMultipleIndicator = "BallScaleRippleMultipleIndicator";
        String BallSpinFadeLoaderIndicator = "BallSpinFadeLoaderIndicator";
        String LineSpinFadeLoaderIndicator = "LineSpinFadeLoaderIndicator";
        String TriangleSkewSpinIndicator = "TriangleSkewSpinIndicator";
        String PacmanIndicator = "PacmanIndicator";
        String BallGridBeatIndicator = "BallGridBeatIndicator";
        String SemiCircleSpinIndicator = "SemiCircleSpinIndicator";
        String CustomIndicator = "CustomIndicator";
    }
}
