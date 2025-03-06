package com.example.denquizgame.game

import com.example.denquizgame.IntCache

interface GameRepository {

    fun questionAndChoices(): QuestionAndChoices
    fun saveUserChoice(index: Int)
    fun check(): CorrectAndUserChoiceIndexes
    fun next()
    fun isLastQuestion(): Boolean
    fun clear()

    // Хранилище данных репозитория(два QuestionAndChoices)
    class Base(
        private val corrects: IntCache,
        private val incorrects: IntCache,
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
            val correctIndex = questionAndChoices().correctIndex

            if (userChoiceIndex.read() == correctIndex) {
                corrects.save(corrects.read() + 1)
            } else {
                incorrects.save(incorrects.read() + 1)
            }

            return CorrectAndUserChoiceIndexes(
                correctIndex = correctIndex,
                //userChoiceIndex = userChoiceIndex
                userChoiceIndex = userChoiceIndex.read()
            )
        }

        override fun next() {
            userChoiceIndex.save(-1)
            index.save(index.read() + 1)

        }

        override fun isLastQuestion(): Boolean = index.read() == list.size

        override fun clear() {
            userChoiceIndex.save(-1)
            index.save(0)
        }
    }


}
