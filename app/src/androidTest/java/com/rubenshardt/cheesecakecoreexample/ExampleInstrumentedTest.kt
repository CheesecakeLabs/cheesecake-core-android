package com.rubenshardt.cheesecakecoreexample

import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.InstrumentationRegistry.getTargetContext

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = getTargetContext()
        assertEquals("com.rubenshardt.cheesecakecoreexample", appContext.packageName)
    }
}
