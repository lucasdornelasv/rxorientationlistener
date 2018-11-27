package com.lucasdornelasv.rxorientationlistener;

import android.hardware.SensorManager;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;


@IntDef({
        SensorManager.SENSOR_DELAY_FASTEST,
        SensorManager.SENSOR_DELAY_GAME,
        SensorManager.SENSOR_DELAY_NORMAL,
        SensorManager.SENSOR_DELAY_UI,
})
@Retention(RetentionPolicy.SOURCE)
@Target({PARAMETER})
public @interface OrientationRate {
}
