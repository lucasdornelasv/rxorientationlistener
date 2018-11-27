package com.lucasdornelasv.rxorientationlistener;

import io.reactivex.Observable;

public interface IRxOrientationListener {

    Observable<Integer> listenOrientation(@OrientationRate int rate);
    Observable<Integer> listenOrientation();

    Observable<IRotation> listenRotation(@OrientationRate int rate);
    Observable<IRotation> listenRotation();

}
