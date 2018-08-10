package com.lucasdornelasv.rxorientationlistener;

import android.view.Surface;

public class RotationImpl implements IRotation {
    //region FIELDS
    private final int orientation;
    private final int rotation;
    //endregion

    //region CONSTRUCTORS
    RotationImpl(int orientation) {
        this.orientation = orientation;
        this.rotation = getSurfaceRotation(orientation);
    }
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    public boolean isPortrait() {
        return rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180;
    }

    @Override
    public boolean isLandscape() {
        return !isPortrait();
    }

    @Override
    public int rotation() {
        return rotation;
    }

    @Override
    public int orientation() {
        return orientation;
    }
    //endregion

    //region PRIVATE METHODS
    private int getSurfaceRotation(int orientation) {
        int aux = getDeviceOrientationRounded(orientation);
        switch (aux) {
            case 90:
                return Surface.ROTATION_90;
            case 180:
                return Surface.ROTATION_180;
            case 270:
                return Surface.ROTATION_270;
            case 0:
            default:
                return Surface.ROTATION_0;
        }
    }

    private int getDeviceOrientationRounded(int orientation) {
        return (orientation + 45) / 90 * 90;
    }
    //endregion

    //endregion
}
