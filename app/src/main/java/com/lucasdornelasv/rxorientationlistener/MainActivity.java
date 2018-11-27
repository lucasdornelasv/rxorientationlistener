package com.lucasdornelasv.rxorientationlistener;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {
    //region FIELDS
    private TextView tvOrientation;

    private Disposable disposable;
    //endregion

    //region METHODS

    //region OVERRIDE METHODS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOrientation = findViewById(R.id.activity_main_tv_orientation);

        RxOrientationListener orientationListener = new RxOrientationListener(this);

        disposable = orientationListener
                .listenRotation()
                .subscribe(rotation -> {
                    tvOrientation.setText(new StringBuilder("Orientation: ")
                            .append(rotation.orientation())
                            .append("\nPortrait: ")
                            .append(rotation.isPortrait())
                            .toString());
                });
    }

    @Override
    protected void onDestroy() {
        disposable.dispose();
        super.onDestroy();
    }
    //endregion

    //endregion
}
