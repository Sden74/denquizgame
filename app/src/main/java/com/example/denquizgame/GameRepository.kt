package com.example.denquizgame

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()

    // Хранилище данных репозитория(два QuestionAndChoices)
    class Base(

        private val index: IntCache,
        private val userChoiceIndex: IntCache,
        private val list: List<QuestionAndChoices> = listOf(
            QuestionAndChoices(
                question = "What color is the sky?",
                choices = listOf("blue", "green", "red", "yellow"),
                correctIndex = 0
            ),
            QuestionAndChoices(
                question = "What color is the grass?",
                choices = listOf("green", "blue", "red", "yellow"),
                correctIndex = 0
            )
        )

    ) : GameRepository {


        //private var index = 0

        // 1  Возврат обертки (questionAndChoices) вопроса и списка ответов по индексу от пользователя
        override fun questionAndChoices(): QuestionAndChoices {
            //return list[index]
            return list[index.read()]
        }

        //private var userChoiceIndex = -1

        // Храним в репе юзерский выбор
        override fun saveUserChoice(index: Int) {
            //userChoiceIndex = index
            userChoiceIndex.save(index)
        }

        // чек вернет обертку (CorrectAndUserChoiceIndexes) из юзерского и корректного индексов
        override fun check(): CorrectAndUserChoiceIndexes {
            return CorrectAndUserChoiceIndexes(
                correctIndex = questionAndChoices().correctIndex,
                //userChoiceIndex = userChoiceIndex
                userChoiceIndex = userChoiceIndex.read()
            )
        }

        override fun next() {
            //userChoiceIndex = -1
            userChoiceIndex.save(-1)

            /*
            if (index + 1 == list.size)
                index = 0
            else
                index++
                */
            if (index.read() + 1 == list.size)
                index.save(0)
            else
                index.save(index.read() + 1)

        }
    }
}
