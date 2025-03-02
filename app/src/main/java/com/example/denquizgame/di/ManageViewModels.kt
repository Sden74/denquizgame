package com.example.denquizgame.di

import com.example.denquizgame.MyViewModel

interface ManageViewModels : ProvideViewModel, ClearViewModel {

    class Factory(
        private val make: ProvideViewModel
    ) : ManageViewModels {
        private val viewModelsMap = mutableMapOf<Class<out MyViewModel>, MyViewModel?>()
        override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T =
            if (viewModelsMap[clazz] == null) {
                val viewModel = make.makeViewModel(clazz)
                viewModelsMap[clazz] = viewModel
                viewModel
            } else
                viewModelsMap[clazz] as T

        override fun clear(viewModelClass: Class<out MyViewModel>) {
            viewModelsMap[viewModelClass] = null
        }
    }
}