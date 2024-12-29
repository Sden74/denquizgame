package com.example.denquizgame.game

import android.view.View
import android.widget.LinearLayout
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom

import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent

import org.hamcrest.Matcher


class GamePage(question: String, choices: List<String>) {

    private val containerIdMatcher: Matcher<View> = withParent(withId(R.id.rootLayout))
    private val classTypeMatcher: Matcher<View> =
        withParent(isAssignableFrom(LinearLayout::class.java))

    private val questionUi = QuestionUi(
        text = question,
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val choicesUiList = choices.map {
        ChoiceUi(
            text = it,
            containerIdMatcher = containerIdMatcher,
            containerClassTypeMatcher = classTypeMatcher
        )
    }

    private val checkUi = ButtonUi(
        textResId = R.string.check,
        colorHex = "#E162EC",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )
    private val nextUi = ButtonUi(
        textResId = R.string.next,
        colorHex = "#1B8B85",
        containerIdMatcher = containerIdMatcher,
        containerClassTypeMatcher = classTypeMatcher
    )

    fun assertAskedQuestionState() {
        questionUi.assertTextVisible()
        choicesUiList.forEach {
            it.assertAvailableToChooseState()
        }
        checkUi.assertNotVisible()
        nextUi.assertNotVisible()

    }

    fun clickNext() {
        TODO("Not yet implemented")
    }

    fun assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        choicesUiList[1].assertIncorrectState()
        choicesUiList[2].assertNotAvailableToCooseState()
        choicesUiList[3].assertNotAvailableToCooseState()
        checkUi.assertNotVisible()
        nextUi.assertVisible()
    }

    fun clickCheck() {
        checkUi.click()
    }

    fun assertSecondChoiceMadeState() {
        questionUi.assertTextVisible()
        choicesUiList.forEachIndexed { index, choiceUi ->
            if (index == 1) choiceUi.assertNotAvailableToCooseState()
            else choiceUi.assertAvailableToChooseState()
        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }

    fun clickSecondChoise() {
        choicesUiList[1].click()
    }


    fun clickFirstChoice() {
        choicesUiList.first().click()
    }

    fun assertFirstChoiceMadeState() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertNotAvailableToCooseState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertAvailableToChooseState()
        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }

    fun assertAnswerCheckedStateFirstIsCorrect() {
        questionUi.assertTextVisible()
        choicesUiList.first().assertCorrectState()
        for (i in 1 until choicesUiList.size) {
            choicesUiList[i].assertNotAvailableToChooseState()
        }
        checkUi.assertVisible()
        nextUi.assertNotVisible()
    }
}