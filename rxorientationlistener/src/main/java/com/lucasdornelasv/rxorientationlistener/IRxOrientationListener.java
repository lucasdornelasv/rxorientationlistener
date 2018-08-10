package com.lucasdornelasv.rxorientationlistener;

import io.reactivex.Observable;

public interface IRxOrientationListener {

    Observable<Integer> listenOrientation();

    Observable<IRotation> listenRotation();

}
