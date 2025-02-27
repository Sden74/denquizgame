package com.example.denquizgame.game


import com.example.denquizgame.stats.NavigateToGameOver
import com.example.denquizgame.views.choice.ChoiceUiState
import com.example.denquizgame.views.choice.UpdateChoiceButton
import com.example.denquizgame.views.question.UpdateText
import com.example.denquizgame.views.visiblebutton.UpdateVisibility
import com.example.denquizgame.views.visiblebutton.VisibilityUiState
import java.io.Serializable

interface GameUiState : Serializable {
    fun update(
        questionTextView: UpdateText,
        firstChoiceButton: UpdateChoiceButton,
        secondChoiceButton: UpdateChoiceButton,
        thirdChoiceButton: UpdateChoiceButton,
        forthChoiceButton: UpdateChoiceButton,
        nextButton: UpdateVisibility,
        checkButton: UpdateVisibility
    ) = Unit

    fun navigate(navigate: NavigateToGameOver) = Unit

    object Empty : GameUiState

    object Finish : GameUiState {
        override fun navigate(navigate: NavigateToGameOver) {
            navigate.navigateToGameOver()
        }
    }

    //fun update(binding: ActivityMainBinding) //todo remove


    /*abstract class Abstract(
        private val questionText: String,
        private val choicesStateList: List<ChoiceUiState>,
        private val checkVisibility: Int,
        private val nextVisibility: Int
    ) : GameUiState {
        override fun update(binding: ActivityMainBinding) = with(binding) {
            questionTextView.text = questionText
            choicesStateList[0].update(firstChoiceButton)
            choicesStateList[1].update(secondChoiceButton)
            choicesStateList[2].update(thirdChoiceButton)
            choicesStateList[3].update(forthChoiceButton)
            checkButton.visibility = checkVisibility
            nextButton.visibility = nextVisibility
        }

        override fun update(questionTextView: UpdateText) {
            questionTextView.updateText(questionText)
        }
    }
*/


    data class AskedQuestion(
        private val question: String,
        private val choices: List<String>
    ) : GameUiState {
        /*override fun update(binding: ActivityMainBinding) {
            TODO("Not yet implemented")
        }

        override fun update(questionTextView: UpdateText) {
            TODO("Not yet implemented")
        }*/
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            questionTextView.updateText(question)
            firstChoiceButton.updateState(ChoiceUiState.Initial(choices[0]))
            secondChoiceButton.updateState(ChoiceUiState.Initial(choices[1]))
            thirdChoiceButton.updateState(ChoiceUiState.Initial(choices[2]))
            forthChoiceButton.updateState(ChoiceUiState.Initial(choices[3]))
            nextButton.update(VisibilityUiState.Gone)
            checkButton.update(VisibilityUiState.Gone)
        }
    }

    data class ChoiceMade(
        //private val question: String,
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.updateState(choices[0])
            secondChoiceButton.updateState(choices[1])
            thirdChoiceButton.updateState(choices[2])
            forthChoiceButton.updateState(choices[3])
            checkButton.update(VisibilityUiState.Visible)
        }
    }


    data class AnswerChecked(
        //private val question: String,
        private val choices: List<ChoiceUiState>
    ) : GameUiState {
        override fun update(
            questionTextView: UpdateText,
            firstChoiceButton: UpdateChoiceButton,
            secondChoiceButton: UpdateChoiceButton,
            thirdChoiceButton: UpdateChoiceButton,
            forthChoiceButton: UpdateChoiceButton,
            nextButton: UpdateVisibility,
            checkButton: UpdateVisibility
        ) {
            firstChoiceButton.updateState(choices[0])
            secondChoiceButton.updateState(choices[1])
            thirdChoiceButton.updateState(choices[2])
            forthChoiceButton.updateState(choices[3])
            checkButton.update(VisibilityUiState.Gone)
            nextButton.update(VisibilityUiState.Visible)
        }
    }
}
