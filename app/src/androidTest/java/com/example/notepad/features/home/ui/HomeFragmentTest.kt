package com.example.notepad.features.home.ui

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notepad.R
import com.example.notepad.features.note.ui.NoteFragment
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.regex.Matcher


@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Test
    fun test_addNote_btn() {
        val scenario = FragmentScenario.launchInContainer(HomeFragment::class.java)
        onView(withId(R.id.btn_addNote_home)).perform(forceClick())
        scenario.recreate()
//         You can also put check() which verifies the content of the given view.
//         Say you want to check the text written on the button or check if textView content has changed or not,
//         in that case, we run:
        onView(withId(R.id.btn_addNote_home)).check(matches(withText("AddNote")))
//         A simple example is checking a View is displayed on when the Fragment is launched.
//         onView(withId(R.id.tv_title_home)).check(matches(isDisplayed()))

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