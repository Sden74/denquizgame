package com.example.denquizgame

class GameViewModel(private val repository: GameRepository) {
    fun chooseFirst(): GameUiState {
        repository.saveUserChoice(0)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            listOf(
                ChoiceUiState.NotAvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3])
            )
        )
    }

    fun chooseSecond(): GameUiState {
        repository.saveUserChoice(1)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.NotAvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3])
            )
        )
    }

    fun chooseThird(): GameUiState {
        repository.saveUserChoice(2)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.NotAvailableToChoose(data.choices[2]),
                ChoiceUiState.AvailableToChoose(data.choices[3])
            )
        )
    }

    fun chooseForth(): GameUiState {
        repository.saveUserChoice(3)
        val data = repository.questionAndChoices()
        return GameUiState.ChoiceMade(
            data.question,
            listOf(
                ChoiceUiState.AvailableToChoose(data.choices[0]),
                ChoiceUiState.AvailableToChoose(data.choices[1]),
                ChoiceUiState.AvailableToChoose(data.choices[2]),
                ChoiceUiState.NotAvailableToChoose(data.choices[3])
            )
        )
    }

    fun check(): GameUiState {
        val data = repository.questionAndChoices()
        val correctAndUserChoiceIndexes = repository.check()
        return GameUiState.AnswerChecked(
            data.question,
            data.choices.mapIndexed { index, choice ->
                if (//correctAndUserChoiceIndexes.userChoiceIndex==index &&
                //if(correctAndUserChoiceIndexes.userChoiceIndex==index &&
                    correctAndUserChoiceIndexes.correctIndex == index)
                    ChoiceUiState.Correct(text = choice)
                else if (correctAndUserChoiceIndexes.userChoiceIndex == index)
                    ChoiceUiState.Incorrect(text = choice)
                else ChoiceUiState.NotAvailableToChoose(text = choice)
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
        return init()
    }

    fun init(): GameUiState {
        val data = repository.questionAndChoices()
        return GameUiState.AskedQuestion(
            data.question,
            data.choices
        )
    }

}
