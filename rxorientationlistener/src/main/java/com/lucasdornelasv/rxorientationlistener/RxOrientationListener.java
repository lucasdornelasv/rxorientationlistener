package com.lucasdornelasv.rxorientationlistener;

import android.content.Context;
import android.hardware.SensorManager;

import io.reactivex.Observable;

public class RxOrientationListener implements IRxOrientationListener {
    //region FIELDS
    private static final int RATE_DEFAULT = SensorManager.SENSOR_DELAY_NORMAL;

    private final Context context;
    //endregion

    //region CONSTRUCTORS
    public RxOrientationListener(Context context) {
        this.context = context;
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public Observable<Integer> listenOrientation(@OrientationRate int rate) {
        return listenOrientationInternal(rate);
    }

    @Override
    public Observable<Integer> listenOrientation() {
        return listenOrientationInternal(RATE_DEFAULT);
    }

    @Override
    public Observable<IRotation> listenRotation(@OrientationRate int rate) {
        return listenOrientationInternal(rate)
                .map(RotationImpl::new);
    }

    @Override
    public Observable<IRotation> listenRotation() {
        return listenRotation(RATE_DEFAULT);
    }
    //endregion

    //region PRIVATE METHODS
    private Observable<Integer> listenOrientationInternal(@OrientationRate int rate) {
        return new RxOrientationObservable(context, rate);
    }
    //endregion

    //endregion
}
