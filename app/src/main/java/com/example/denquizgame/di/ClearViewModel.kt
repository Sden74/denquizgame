package com.example.denquizgame.di

import com.example.denquizgame.MyViewModel

interface ClearViewModel {
    fun clear(viewModelClass: Class<out MyViewModel>)
}