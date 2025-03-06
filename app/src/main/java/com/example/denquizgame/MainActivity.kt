package com.example.denquizgame

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.denquizgame.di.ProvideViewModel
import com.example.denquizgame.game.Navigate


class MainActivity() : AppCompatActivity(), Navigate, ProvideViewModel {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigateToGame()
        }
    }

    override fun navigate(screen: Screen) = screen.show(R.id.container, supportFragmentManager)

    override fun <T : MyViewModel> makeViewModel(clazz: Class<T>): T =
        (application as ProvideViewModel).makeViewModel(clazz)

}
