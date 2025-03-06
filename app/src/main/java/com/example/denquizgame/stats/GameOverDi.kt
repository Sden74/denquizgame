package com.example.denquizgame.stats

import com.example.denquizgame.IntCache
import com.example.denquizgame.di.AbstractProvideViewModel
import com.example.denquizgame.di.Core
import com.example.denquizgame.di.Module
import com.example.denquizgame.di.ProvideViewModel

class GameOverModule(private val core: Core) : Module<GameOverViewModel> {
    override fun viewModel(): GameOverViewModel {
        val corrects = IntCache.Base(core.sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(core.sharedPreferences, "incorrects", 0)
        return GameOverViewModel(
            core.clearViewModel,
            StatsRepository.Base(
                corrects,
                incorrects
            )
        )
    }

}

class ProvideGameOverViewModel(
    core: Core,
    next: ProvideViewModel
) : AbstractProvideViewModel(core, next, GameOverViewModel::class.java) {
    override fun module(): Module<*> = GameOverModule(core)
}