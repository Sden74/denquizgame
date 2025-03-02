package com.example.denquizgame.game

import com.example.denquizgame.MyViewModel
import com.example.denquizgame.di.ClearViewModel
import com.example.denquizgame.views.choice.ChoiceUiState

class GameViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: GameRepository
) : MyViewModel {
    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.choices.mapIndexed { index, _ ->
                if (index == 0)
                    ChoiceUiState.NotAvailableToChoose
                else
                    ChoiceUiState.AvailableToChoose
            }
        )

    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            /*data.question,
            listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.NotAvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3])*/
            data.choices.mapIndexed { index, _ ->
                if (index == 1)
                    ChoiceUiState.NotAvailableToChoose
                else
                    ChoiceUiState.AvailableToChoose
            }
        )

    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            /*data.question,
            listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.NotAvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3])*/
            data.choices.mapIndexed { index, _ ->
                if (index == 2)
                    ChoiceUiState.NotAvailableToChoose
                else
                    ChoiceUiState.AvailableToChoose
            }
        )

    }

    fun chooseForth(): GameUiState {
        repository.saveUserChoice(3)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            /*data.question,
            listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.NotAvailableToChoose(data.choices[3])*/
            data.choices.mapIndexed { index, _ ->
                if (index == 3)
                    ChoiceUiState.NotAvailableToChoose
                else
                    ChoiceUiState.AvailableToChoose
            }
        )
        //)
    }

    fun check(): GameUiState {
        val data = repository.questionAndChoices()
        val correctAndUserChoiceIndexes = repository.check()
        return GameUiState.AnswerChecked(
            //data.question,
            //data.choices.mapIndexed { index, choice ->
            data.choices.mapIndexed { index, _ ->
                if (//correctAndUserChoiceIndexes.userChoiceIndex==index &&
                //if(correctAndUserChoiceIndexes.userChoiceIndex==index &&
                    correctAndUserChoiceIndexes.correctIndex == index)
                //ChoiceUiState.Correct(text = choice)
                    ChoiceUiState.Correct
                else if (correctAndUserChoiceIndexes.userChoiceIndex == index)
                //ChoiceUiState.Incorrect(text = choice)
                    ChoiceUiState.Incorrect
                //else ChoiceUiState.NotAvailableToChoose(text = choice)
                else ChoiceUiState.NotAvailableToChoose
            }

            /*listOf(
                if(correctAndUserChoiceIndexes.userChoiceIndex==0 && correctAndUserChoiceIndexes.correctIndex==0)
                    ChoiceUiState.Correct(text = data.choices[0])
                else if (correctAndUserChoiceIndexes.userChoiceIndex==0)
                    ChoiceUiState.Incorrect(text = data.choices[0])
                else ChoiceUiState.NotAvailableToChoose(text = data.choices[0])
                ,
                if(correctAndUserChoiceIndexes.userChoiceIndex==1 && correctAndUserChoiceIndexes.correctIndex==1)
                    ChoiceUiState.Correct(text = data.choices[1])
                else if (correctAndUserChoiceIndexes.userChoiceIndex==1)
                    ChoiceUiState.Incorrect(text = data.choices[1])
                else ChoiceUiState.NotAvailableToChoose(text = data.choices[1])
                ,
                if(correctAndUserChoiceIndexes.userChoiceIndex==2 && correctAndUserChoiceIndexes.correctIndex==2)
                    ChoiceUiState.Correct(text = data.choices[2])
                else if (correctAndUserChoiceIndexes.userChoiceIndex==2)
                    ChoiceUiState.Incorrect(text = data.choices[2])
                else ChoiceUiState.NotAvailableToChoose(text = data.choices[2])
                ,
                if(correctAndUserChoiceIndexes.userChoiceIndex==3 && correctAndUserChoiceIndexes.correctIndex==3)
                    ChoiceUiState.Correct(text = data.choices[3])
                else if (correctAndUserChoiceIndexes.userChoiceIndex==3)
                    ChoiceUiState.Incorrect(text = data.choices[3])
                else ChoiceUiState.NotAvailableToChoose(text = data.choices[3])

            )*/
        )

    }

    fun next(): GameUiState {
        repository.next()
        return if (repository.isLastQuestion()) {
            repository.clear()
            clearViewModel.clear(GameViewModel::class.java)
            return GameUiState.Finish
        } else
            init()

    }

    fun init(firstRun: Boolean = true): GameUiState {
        if (firstRun) {
            val data = repository.questionAndChoices()
            return GameUiState.AskedQuestion(
                data.question,
                data.choices
            )
        } else
            return GameUiState.Empty
    }

}
