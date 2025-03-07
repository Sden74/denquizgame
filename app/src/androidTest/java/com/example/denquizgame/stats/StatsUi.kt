package com.example.denquizgame.stats

import android.view.View
import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.denquizgame.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class StatsUi(
    incorrects: Int,
    corrects: Int,
    containerIdMatcher: Matcher<View>,
    classTypeMatcher: Matcher<View>
) {
    fun assertVisible() {
        interaction.check(matches(isDisplayed()))
    }

    fun assertDoesNotExist() {
        interaction.check(doesNotExist())
    }

    private val interaction: ViewInteraction =
        onView(
            allOf(
                withId(R.id.statsTextView),
                isAssignableFrom(TextView::class.java),
                withText("Game Over\n\nCorrects: $corrects\nIncorrects: $incorrects"),
                containerIdMatcher,
                classTypeMatcher,

                )

        )

}
