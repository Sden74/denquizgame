package com.example.denquizgame.di

import com.example.denquizgame.MyViewModel

interface Module<T : MyViewModel> {
    fun viewModel(): T
}