package com.example.denquizgame.stats


interface StatsRepository {

    fun stats(): Pair<Int, Int>

}