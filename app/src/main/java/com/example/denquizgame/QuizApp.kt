package com.example.denquizgame

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.denquizgame.game.GameRepository
import com.example.denquizgame.game.GameViewModel
import com.example.denquizgame.stats.GameOverViewModel
import com.example.denquizgame.stats.StatsRepository

class QuizApp : Application() {
    //здесь создавать view-модельку нельзя, т.к. если репе будет нужен контекст то
    // будет краш, т.к. контекст сетится аппе чуть позже, поэтому правильно создавать
    //view-модельку в onCreate()

    //val viewModel = GameViewModel(GameRepository.Base())
    //todo di improve
    lateinit var gameViewModel: GameViewModel
    lateinit var gameOverViewModel: GameOverViewModel

    init {
        Log.d("sdv74", "QuizApp")
    }

    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
        Log.d("sdv74", "onCreate QuizApp")
        val corrects = IntCache.Base(sharedPreferences, "corrects", 0)
        val incorrects = IntCache.Base(sharedPreferences, "incorrects", 0)
        gameViewModel = GameViewModel(
            GameRepository.Base(
                corrects,
                incorrects,
                IntCache.Base(sharedPreferences, "indexKey", 0),
                IntCache.Base(sharedPreferences, "userChoiceIndexKey", -1)
            )
        )
        gameOverViewModel = GameOverViewModel(
            StatsRepository.Base(
                corrects,
                incorrects
            )
        )
    }
}

