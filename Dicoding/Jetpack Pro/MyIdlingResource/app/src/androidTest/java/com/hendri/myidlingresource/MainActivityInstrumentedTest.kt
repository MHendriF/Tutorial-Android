package com.hendri.myidlingresource

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentedTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkText() {
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.activity.getString(R.string.prepare))))
        onView(withText(mActivityRule.activity.getString(R.string.start))).perform(click())
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.activity.getString(R.string.delay1))));
    }
}