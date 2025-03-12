package com.example.denquizgame.load

import com.example.denquizgame.R
import com.example.denquizgame.game.NavigateToGame
import com.example.denquizgame.views.error.ErrorUiState
import com.example.denquizgame.views.error.UpdateError
import com.example.denquizgame.views.visiblebutton.UpdateVisibility
import com.example.denquizgame.views.visiblebutton.VisibilityUiState

interface LoadUiState {


    fun show(
        errorTextView: UpdateError,
        retryButton: UpdateVisibility,
        progressBar: UpdateVisibility
    )

    fun navigate(navigateToGame: NavigateToGame) = Unit

    abstract class Abstract(
        private val errorUiState: ErrorUiState,
        private val retryUiState: VisibilityUiState,
        private val progressUiState: VisibilityUiState
    ) : LoadUiState {
        override fun show(
            errorTextView: UpdateError,
            retryButton: UpdateVisibility,
            progressBar: UpdateVisibility
        ) {
            errorTextView.update(errorUiState)
            retryButton.update(retryUiState)
            progressBar.update(progressUiState)
        }
    }

    object Progress :
        Abstract(
            ErrorUiState.Hide,
            VisibilityUiState.Gone,
            VisibilityUiState.Visible
        )


    object Success :
        Abstract(
            ErrorUiState.Hide,
            VisibilityUiState.Gone,
            VisibilityUiState.Gone
        ) {
        override fun navigate(navigateToGame: NavigateToGame) {
            navigateToGame.navigateToGame()
        }
    }


    data class Error(private val message: String) :
        Abstract(
            ErrorUiState.Show(R.string.no_internet_connection),
            VisibilityUiState.Visible,
            VisibilityUiState.Gone
        )

}

