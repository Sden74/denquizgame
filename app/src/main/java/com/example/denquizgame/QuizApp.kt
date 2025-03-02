package com.example.denquizgame

import android.app.Application
import com.example.denquizgame.di.ClearViewModel
import com.example.denquizgame.di.Core
import com.example.denquizgame.di.ManageViewModels
import com.example.denquizgame.di.ProvideViewModel

class QuizApp : Application(), ProvideViewModel {
    //здесь создавать view-модельку нельзя, т.к. если репе будет нужен контекст то
    // будет краш, т.к. контекст сетится аппе чуть позже, поэтому правильно создавать
    //view-модельку в onCreate()

    //val viewModel = GameViewModel(GameRepository.Base())
    private lateinit var factory: ManageViewModels




    override fun onCreate() {
        super.onCreate()
        val clearViewModel = object : ClearViewModel {
            override fun clear(viewModelClass: Class<out MyViewModel>) =
                factory.clear(viewModelClass)
        }
        val make = ProvideViewModel.Make(Core(this, clearViewModel))
        factory = ManageViewModels.Factory(make)
    }

    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T = factory.makeViewModel(clazz)
}


