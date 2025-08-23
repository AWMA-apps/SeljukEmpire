package com.awma.seljukempire

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.awma.seljukempire.presentation.ui.MainActivity
import org.hamcrest.CoreMatchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activeRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.awma.seljukempire", appContext.packageName)
    }

    @Test
    fun checkProgressAndRecyclerView() {
        onView(withId(R.id.view_pager)).perform(swipeLeft())

        onView(allOf(withId(R.id.progressBar), isDisplayed()))
            .check(matches(isDisplayed()))

        Thread.sleep(4000) // Wait for a short duration

        onView(
            allOf(
                withId(R.id.recyclerView),
                isCompletelyDisplayed()
            )
        ).check(RecyclerViewItemCountAssertion(atLeast = 1))

        onView(
            allOf(
                withId(R.id.progressBar),
                isCompletelyDisplayed()
            )
        ).check(doesNotExist())
//        Thread.sleep(5000) // مؤقتاً عشان تنتظر البيانات
//
//        onView(allOf(
//            withId(R.id.recyclerView),
//            isCompletelyDisplayed()
//        )).check(RecyclerViewItemCountAssertion(atLeast = 1))
        //        try {
//            Thread.sleep(1000) // Wait for a short duration
//        } catch (e: InterruptedException) {
//            e.printStackTrace()
//        }
//        onView(allOf(withId(R.id.progressBar),
//            withEffectiveVisibility(Visibility.GONE)))
//            .check(matches(not(isDisplayed())))
    }
}