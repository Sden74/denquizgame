package com.example.denquizgame.game.di

import com.example.denquizgame.IntCache
import com.example.denquizgame.di.AbstractProvideViewModel
import com.example.denquizgame.di.Core
import com.example.denquizgame.di.Module
import com.example.denquizgame.di.ProvideViewModel
import com.example.denquizgame.game.GameRepository
import com.example.denquizgame.game.GameViewModel

class GameModule(private val core: Core) : Module<GameViewModel> {
    override fun viewModel(): GameViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)
        return GameViewModel(
            core.clearViewModel,
            GameRepository.Base(
                corrects,
                incorrects,
                IntCache.Base(core.sharedPreferences, "indexKey", 0),
                IntCache.Base(core.sharedPreferences, "userChoiceIndexKey", -1)
            )
        )
    }
}

class ProvideGameViewModel(core: Core, next: ProvideViewModel) :
    AbstractProvideViewModel(core, next, GameViewModel::class.java) {
    override fun module(): Module<*> = GameModule(core)
}