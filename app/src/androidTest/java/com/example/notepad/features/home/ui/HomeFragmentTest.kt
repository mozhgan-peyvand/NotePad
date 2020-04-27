package com.example.notepad.features.home.ui

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import org.junit.Assert.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.notepad.R
import com.example.notepad.features.note.ui.NoteFragment
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher

/**
 * https://code.tutsplus.com/tutorials/testing-android-user-interfaces-with-espresso--cms-31687
 * */
@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {

    @Test
    fun test_addNote_btn() {
        /**
         * Because we want to test an Activity,
         * we have to do a little setup. We need to inform Espresso which Activity to open or launch before executing and
         * destroy after executing any test method.
         * */

        val scenario = FragmentScenario.launchInContainer(HomeFragment::class.java)

        /**
         * To find widgets in Espresso, we make use of the onView() static method (instead of findViewById())
         * onView(ViewMatcher).perform(ViewAction).check(ViewAssertion)
         * so usefull -> https://developer.android.com/training/testing/espresso/cheat-sheet
         * */
        onView(withId(R.id.btn_addNote_home)).check(matches(withText("AddNote")))
        onView(withId(R.id.btn_addNote_home)).perform(forceClick())
            .check(matches(withText("AddNote")))
        scenario.recreate()

        /**
         * You can also put check() which verifies the content of the given view.
         * Say you want to check the text written on the button or check if textView content has changed or not,
         * in that case, we run:
         * onView(withId(R.id.btn_addNote_home)).check(matches(withText("AddNote")))
         * */


    }

    @Test
    fun test_addNote_in_adapter_home() {
        // test scenario add item to adapter
        val scenario = FragmentScenario.launchInContainer(HomeFragment::class.java)
        onView(withId(R.id.et_titleNote_home)).perform(ViewActions.typeText("TestWriteTitle"))
        onView(withId(R.id.btn_addNote_home)).perform(forceClick())

        //later :)

//        onView(withId(R.id.note_list)).perform(
//            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
//            )
//        )

        /**
         *  A simple example is checking a View is displayed on when the Fragment is launched.
         * */
        onView(withId(R.id.tv_date_home)).check(matches(isDisplayed()))

    }


    fun forceClick(): ViewAction? {
        return object : ViewAction {


            override fun getDescription(): String {
                return "force click"
            }

            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return allOf(isClickable(), isEnabled(), isDisplayed());

            }

            override fun perform(uiController: UiController, view: View) {
                view.performClick() // perform click without checking view coordinates.
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}