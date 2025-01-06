package com.example.denquizgame

import com.example.denquizgame.databinding.ActivityMainBinding

interface GameUiState {

    fun update(binding: ActivityMainBinding): Unit =
        throw IllegalStateException("") //todo remove it

    data class AskedQuestion(
        val question: String,
        val choices: List<String>
    ) : GameUiState {

    }

    data class ChoiceMade(val question: String, val choices: List<ChoiceUiState>) :
        GameUiState {

    }

    data class AnswerChecked(val question: String, val choices: List<ChoiceUiState>) : GameUiState {

    }
}

