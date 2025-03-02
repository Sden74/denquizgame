package com.example.denquizgame.stats

import com.example.denquizgame.MyViewModel
import com.example.denquizgame.di.ClearViewModel
import com.example.denquizgame.views.stats.StatsUiState

class GameOverViewModel(
    private val clearViewModel: ClearViewModel,
    private val repository: StatsRepository
) : MyViewModel {
    fun init(isFirstRun: Boolean): StatsUiState {
        return if (isFirstRun) {
            val (corrects, incorrects) = repository.stats()
            repository.clear()
            StatsUiState.Base(corrects, incorrects)
        } else {
            StatsUiState.Empty
        }
    }
    fun clear() {
        clearViewModel.clear(GameOverViewModel::class.java)
    }
}

