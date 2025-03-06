package com.example.denquizgame.di

import com.example.denquizgame.MyViewModel
import com.example.denquizgame.game.di.ProvideGameViewModel
import com.example.denquizgame.stats.ProvideGameOverViewModel

interface ProvideViewModel {

    fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T

    class Make(core: Core) : ProvideViewModel {

        private var chain: ProvideViewModel

        init {
            chain = Error()
            chain = ProvideGameViewModel(core, chain)
            chain = ProvideGameOverViewModel(core, chain)
        }

        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {

            return chain.makeViewModel(clazz)
        }

    }

    class Error : ProvideViewModel {
        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
            throw IllegalStateException("unknown class $clazz")
        }

    }
}