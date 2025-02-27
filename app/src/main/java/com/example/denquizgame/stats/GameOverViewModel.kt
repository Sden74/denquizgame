package com.example.denquizgame.stats

import com.example.denquizgame.views.stats.StatsUiState

class GameOverViewModel(private val repository: StatsRepository) {
    //todo
    fun statsUiState(): StatsUiState {
        val (corrects, incorrects) = repository.stats()
        return StatsUiState.Base(corrects, incorrects)
    }
}

