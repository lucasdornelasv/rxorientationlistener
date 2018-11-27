package com.lucasdornelasv.rxorientationlistener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class ExampleInstrumentedTest {
    //region FIELDS
    RxOrientationListener rxOrientationListener;
    //endregion

    //region METHODS

    //region TEST METHODS
    @Before
    public void setUp() {
        rxOrientationListener = new RxOrientationListener(RuntimeEnvironment.application);
    }

    @Test
    public void should_detect_portrait() throws InterruptedException {
        rxOrientationListener
                .listenRotation()
                .filter(IRotation::isPortrait)
                .take(1)
                .test()
                .await()
                .assertSubscribed()
                .assertValueCount(1)
                .assertNoErrors()
                .assertComplete()
                .assertValue(IRotation::isPortrait);
    }
    //endregion

    //endregion
}
