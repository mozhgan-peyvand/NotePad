package com.example.notepad.features.home.ui

import androidx.fragment.app.testing.FragmentScenario
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.notepad.MainActivity
import com.example.notepad.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * my lovly course -> https://www.youtube.com/watch?v=QmH-B_UYIyA
* */

@RunWith(AndroidJUnit4ClassRunner::class)
class HomeNavigationTest {
    @Test
    fun test_navigation_to_notePage() {

        //setup Activity : we do that because main activity is the host for the fragments

        var activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // Nav NoteFragment

        onView(withId(R.id.et_titleNote_home)).perform(typeText("mozhgan"))

        onView(withId(R.id.et_titleNote_home)).check(matches(isDisplayed()))

        onView(withId(R.id.btn_addNote_home)).perform(click())

        onView(withId(R.id.note_list)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                ViewMatchers.hasDescendant(ViewMatchers.withText("mozhgan")),
                ViewActions.click()
            )
        )

        //verify

        onView(withId(R.id.ll_notePage)).check(ViewAssertions.matches(isDisplayed()))
        //to back to main page
        pressBack()

    }
}