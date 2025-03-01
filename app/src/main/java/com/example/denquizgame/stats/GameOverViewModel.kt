package com.example.denquizgame.stats

import com.example.denquizgame.views.stats.StatsUiState

class GameOverViewModel(private val repository: StatsRepository) {
    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (corrects, incorrects) = repository.stats()
            repository.clear()
            StatsUiState.Base(corrects, incorrects)
        } else {
            StatsUiState.Empty
        }
    }
}

