package com.example.denquizgame.di

import android.content.Context

class Core(context: Context, val clearViewModel: ClearViewModel) {
    val sharedPreferences = context.getSharedPreferences("quizAppData", Context.MODE_PRIVATE)
}