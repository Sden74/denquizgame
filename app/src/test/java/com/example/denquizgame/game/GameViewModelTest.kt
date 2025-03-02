package com.example.denquizgame.game

import com.example.denquizgame.views.choice.ChoiceUiState
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GameViewModelTest {

    private lateinit var viewModel: GameViewModel
    private lateinit var repository: FakeRepository
    @Before
    fun setup() {
        repository = FakeRepository()
        viewModel = GameViewModel(
            repository = repository//,
            //clearViewModel = TODO()
        )
    }

    /**
     * QGTC-01
     */
    @Test
    fun caseNumber1() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.next()

        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        )
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(false, repository.clearCalled)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            choices = listOf<ChoiceUiState>(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.Finish
        Assert.assertEquals(expected, actual)
        Assert.assertEquals(true, repository.clearCalled)
    }

    /**
     * QGTC-02
     */
    @Test
    fun caseNumber2() {
        var actual: GameUiState = viewModel.init()
        var expected: GameUiState = GameUiState.AskedQuestion(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4")
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.chooseFirst()
        expected = GameUiState.ChoiceMade(
            //question = "q1",
            choices = listOf(
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.chooseSecond()
        expected = GameUiState.ChoiceMade(
            //question = "q1",
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.chooseThird()
        expected = GameUiState.ChoiceMade(
            //question = "q1",
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.AvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.chooseForth()
        expected = GameUiState.ChoiceMade(
            //question = "q1",
            choices = listOf(
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.AvailableToChoose,
                ChoiceUiState.NotAvailableToChoose
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.check()
        expected = GameUiState.AnswerChecked(
            //question = "q1",
            choices = listOf(
                ChoiceUiState.Correct,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.NotAvailableToChoose,
                ChoiceUiState.Incorrect
            )
        )
        Assert.assertEquals(expected, actual)

        actual = viewModel.next()
        expected = GameUiState.AskedQuestion(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4")
        )
        Assert.assertEquals(expected, actual)
    }
}

private class FakeRepository : GameRepository {
    // Хранилище данных репозитория(два QuestionAndChoices)
    private val list: List<QuestionAndChoices> = listOf(
        QuestionAndChoices(
            question = "q1",
            choices = listOf("c1", "c2", "c3", "c4"),
            correctIndex = 0
        ),
        QuestionAndChoices(
            question = "q2",
            choices = listOf("cd1", "cd2", "cd3", "cd4"),
            correctIndex = 0
        )
    )

    private var index = 0

    // 1  Возврат обертки (questionAndChoices) вопроса и списка ответов по индексу от пользователя
    override fun questionAndChoices(): QuestionAndChoices {
        return list[index]
    }

    private var userChoiceIndex = -1

    // Храним в репе юзерский выбор
    override fun saveUserChoice(index: Int) {
        userChoiceIndex = index
    }

    // чек вернет обертку (CorrectAndUserChoiceIndexes) из юзерского и корректного индексов
    override fun check(): CorrectAndUserChoiceIndexes {
        return CorrectAndUserChoiceIndexes(
            correctIndex = questionAndChoices().correctIndex,
            userChoiceIndex = userChoiceIndex
        )
    }

    override fun next() {
        userChoiceIndex = -1
        index++

//        if (isLastQuestion())
//            index = 0
    }

    override fun isLastQuestion(): Boolean {
        return index == list.size
    }

    var clearCalled = false
    override fun clear() {
        clearCalled = true
    }
}