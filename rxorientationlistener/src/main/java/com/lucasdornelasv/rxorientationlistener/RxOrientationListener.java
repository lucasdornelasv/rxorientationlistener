package com.lucasdornelasv.rxorientationlistener;

import android.content.Context;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class RxOrientationListener implements IRxOrientationListener {
    //region FIELDS
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
    public Observable<Integer> listenOrientation() {
        return new RxOrientationObservable(context, 1);
    }

    @Override
    public Observable<IRotation> listenRotation() {
        return listenOrientation()
                .map(RotationImpl::new);
    }
    //endregion

    //endregion
}
