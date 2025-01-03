package com.example.denquizgame

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.denquizgame.game.GamePage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var gamePage : GamePage
    @Before
    fun setup(){
        gamePage = GamePage(
            question = "What color is the sky?",
            choices = listOf("blue", "green", "red", "yellow")
        )
    }

    /**
     * QGTC-01
     */
    @Test
    fun caseNumber1() {
//        val gamePage = GamePage(
//            question = "What color is the sky?",
//            choices = listOf("blue", "green", "red", "yellow")
//        )

        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrect()
    }

    /**
     * QGTC-02
     */
    @Test
    fun caseNumber2(){
//        var gamePage = GamePage(
//            question = "What color is the sky?",
//            choices = listOf("blue", "green", "red", "yellow")
//        )

        gamePage.assertAskedQuestionState()

        gamePage.clickFirstChoice()
        gamePage.assertFirstChoiceMadeState()

        gamePage.clickSecondChoiсe()
        gamePage.assertSecondChoiceMadeState()

        gamePage.clickCheck()
        gamePage.assertAnswerCheckedStateFirstIsCorrectSecondIsIncorrect()

        gamePage.clickNext()
        gamePage = GamePage(
            question = "What color is the grass?",
            choices = listOf("green", "blue", "red", "yellow")
        )
        gamePage.assertAskedQuestionState()
    }
}