package com.example.denquizgame

interface ChoiceUiState {
    data class NotAvailableToChoose(val text: String) : ChoiceUiState {

    }

    data class AvailableToChoose(val text: String) : ChoiceUiState {

    }

    data class Correct(val text: String) : ChoiceUiState {

    }

    data class Incorrect(val text: String) : ChoiceUiState {

    }

}
