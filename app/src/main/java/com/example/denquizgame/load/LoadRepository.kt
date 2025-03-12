package com.example.denquizgame.load

interface LoadRepository {

    fun load(resultCallback: (LoadResult) -> Unit)
}
