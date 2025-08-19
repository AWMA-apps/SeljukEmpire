package com.awma.seljukempire

import android.view.View
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat

@Suppress("DEPRECATION")
class RecyclerViewItemCountAssertion(private val expectedCount: Int = -1, private val atLeast: Int = -1) : ViewAssertion {

    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter, notNullValue())

        if (expectedCount != -1) {
            assertThat(adapter!!.itemCount, `is`(expectedCount))
        } else if (atLeast != -1) {
            assertThat(adapter!!.itemCount >= atLeast, `is`(true))
        }
    }
}