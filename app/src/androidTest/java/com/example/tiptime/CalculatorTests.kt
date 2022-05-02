package com.example.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isChecked
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//Instrumentation tests require an InstrumentationTestRunner,
// which allows the test to execute on a device or emulator.
// There are several other instrumentation runners, but for this
// test we'll use the AndroidJUnit4 test runner.

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()

    //ActivityScenarioRule comes from the AndroidX Test library.
    // It tells the device to launch an activity specified by the developer.
    // You'll need to annotate it with @get:Rule, which specifies that the subsequent
    // rule, in this case launching an activity, should execute before every test in the class.
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip(){

        //find a UI component to interact with, using the onView(). The function takes a ViewMatcher
        //object parameter A ViewMatcher is essentially a UI component that matches a particular
        // criteria, which in this case is a component that has the ID

        //basically this test grabs the cost of service input, writes in 50.00 gets rid of the keyboard
        //grabs the calculate button and clicks it
        //and tests to make sure the
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result)).check(matches(withText(containsString("$10.00"))))
    }

    fun calculate_18_percent_tip(){
        onView(withId(R.id.cost_of_service_edit_text)).perform(typeText("50.00")).perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.option_eighteen_percent)).perform(click())
        onView(withId(R.id.calculate_button))
            .perform(click())
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$9.00"))))
    }
}