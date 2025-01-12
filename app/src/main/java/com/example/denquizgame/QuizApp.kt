package com.example.denquizgame

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    //здесь создавать view-модельку нельзя, т.к. если репе будет нужен контекст то
    // будет краш, т.к. контекст сетится аппе чуть позже, поэтому правильно создавать
    //view-модельку в onCreate()

    //val viewModel = GameViewModel(GameRepository.Base())

    lateinit var viewModel: GameViewModel

    init {
        Log.d("sdv74", "QuizApp")
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("sdv74", "onCreate QuizApp")
        viewModel = GameViewModel(GameRepository.Base())
    }
}
