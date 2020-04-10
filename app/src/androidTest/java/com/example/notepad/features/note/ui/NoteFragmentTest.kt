package com.example.notepad.features.note.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notepad.R
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

/*
links:
https://www.xwiki.org/xwiki/bin/view/Blog/Android%20UI%20Unit%20Testing%20with%20Espresso
*/

@RunWith(AndroidJUnit4::class)
 class NoteFragmentTest{
    @Test
    fun test_title_note_editText(){
     val scenario =    FragmentScenario.launchInContainer(NoteFragment::class.java)
        //Here interaction is done by Espresso. Espresso helps us to simulate the user interaction with UI.
        // It can typeText, clear text, click on the button, scroll to a list, swipe left, swipe right, etc. In summary,
        // it can perform all user interactions that the user does on the UI which is very simple to use. For example:
        onView(withId(R.id.et_title_notePage)).perform(typeText("TestWriteTitle"))
        onView(withId(R.id.et_note)).perform(typeText("mySpecialNoteAbout"))
    }

    @Test
    fun test_save_cancel_button(){
        val scenario = FragmentScenario.launchInContainer(NoteFragment::class.java)
        onView(withId(R.id.btn_save_note)).perform(forceClick())
        onView(withId(R.id.btn_cancel_note)).perform(forceClick())
        onView(withId(R.id.btn_cancel_note)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    "cancel"
                )
            )
        )
        onView(withId(R.id.btn_save_note)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    "save"
                )
            )
        )


    }
    fun forceClick(): ViewAction? {
        return object : ViewAction {


            override fun getDescription(): String {
                return "force click"
            }

            override fun getConstraints(): org.hamcrest.Matcher<View> {
                return CoreMatchers.allOf(
                    ViewMatchers.isClickable(),
                    ViewMatchers.isEnabled(),
                    ViewMatchers.isDisplayed()
                );

            }

            override fun perform(uiController: UiController, view: View) {
                view.performClick() // perform click without checking view coordinates.
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
//    protected fun onSaveInstanceState(outState: Bundle) {
//        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE")
//        super.onSaveInstanceState(outState)
//    }
}