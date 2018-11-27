package com.lucasdornelasv.rxorientationlistener;

import android.content.Context;
import android.view.OrientationEventListener;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxOrientationObservable extends Observable<Integer> {
    //region FIELDS
    private final Context context;
    private final int rate;
    //endregion

    //region CONSTRUCTORS
    RxOrientationObservable(Context context, @OrientationRate int rate) {
        this.context = context;
        this.rate = rate;
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    protected void subscribeActual(Observer<? super Integer> observer) {
        try {
            final OrientationListener listener = new OrientationListener(observer, context, rate);
            observer.onSubscribe(listener);
            if (listener.canDetectOrientation()) {
                listener.enable();
            } else {
                throw new IOException("Cannot detect orientation");
            }
        } catch (Throwable e) {
            observer.onError(e);
        }
    }
    //endregion

    //endregion

    //region CLASSES
    static class OrientationListener extends OrientationEventListener implements Disposable {
        //region FIELDS
        private final Observer<? super Integer> observer;
        private final AtomicBoolean unsubscribed = new AtomicBoolean();
        //endregion

        //region CONSTRUCTORS
        private OrientationListener(Observer<? super Integer> observer, Context context, int rate) {
            super(context, rate);
            this.observer = observer;
        }
        //endregion

        //region METHODS

        //region OVERRIDE METHODS
        @Override
        public void onOrientationChanged(int i) {
            if (unsubscribed.get()) return;
            observer.onNext(i);
        }

        @Override
        public void dispose() {
            if (unsubscribed.get()) return;
            unsubscribed.set(true);
            if (canDetectOrientation()) disable();
        }

        @Override
        public boolean isDisposed() {
            return unsubscribed.get();
        }
        //endregion

        //endregion
    }
    //endregion
}
