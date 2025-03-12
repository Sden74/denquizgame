package com.example.denquizgame.load

import com.example.denquizgame.views.error.UpdateError
import com.example.denquizgame.views.visiblebutton.UpdateVisibility

interface LoadUiState {


    abstract fun show(
        errorTextView: UpdateError,
        retryButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    object Progress : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            TODO("Not yet implemented")
        }

    }

    object Success : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            TODO("Not yet implemented")
        }

    }

    data class Error(private val message: String) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            TODO("Not yet implemented")
        }

    }

}
