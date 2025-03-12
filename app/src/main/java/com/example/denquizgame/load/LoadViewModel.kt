package com.example.denquizgame.load

import com.example.denquizgame.MyViewModel

class LoadViewModel(
    private val repository: LoadRepository,
    private val observable: UiObservable
) : MyViewModel {
    fun load(isFirstRun: Boolean = true) {
        if (isFirstRun) {
            observable.postUiState(LoadUiState.Progress)
            repository.load {
                if (it.isSuccessful()) {
                    observable.postUiState(LoadUiState.Success)
                } else {
                    observable.postUiState(LoadUiState.Error(it.message()))
                }
            }
        }
    }

    fun startUpdates(observer: (LoadUiState) -> Unit) {
        observable.register(observer)
    }

    fun stopUpdates() {
        observable.unregister()
    }

}
