package com.lucasdornelasv.rxorientationlistener;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //region FIELDS
    RxOrientationListener rxOrientationListener;
    //endregion

    //region METHODS

    //region TEST METHODS
    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getTargetContext();
        rxOrientationListener = new RxOrientationListener(appContext);
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

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.lucasdornelasv.rxorientationlistener.test", appContext.getPackageName());
    }
    //endregion

    //endregion
}
