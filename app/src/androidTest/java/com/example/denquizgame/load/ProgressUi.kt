package com.example.denquizgame.load

import android.view.View
import android.widget.ProgressBar
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.denquizgame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matcher

class ProgressUi(
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {
    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertNotVisible() {
        interaction.check(matches(not(isDisplayed())))
    }

    private val interaction: ViewInteraction = onView(
        allOf(
            withId(R.id.progressBar),
            isAssignableFrom(ProgressBar::class.java),
            containerIdMatcher,
            classTypeMatcher
        )
    )
}
