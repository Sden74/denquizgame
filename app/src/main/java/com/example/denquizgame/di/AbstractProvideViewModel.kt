package com.example.denquizgame.di

import com.example.denquizgame.MyViewModel

abstract class AbstractProvideViewModel(
    protected val core: Core,
    private val nextChain: ProvideViewModel,
    private val viewModelClass: Class<out MyViewModel>
) : ProvideViewModel {
    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T {
        return if (clazz == viewModelClass)
            module().viewModel() as T
        else
            nextChain.makeViewModel(clazz)
    }

    protected abstract fun module(): Module<*>
}