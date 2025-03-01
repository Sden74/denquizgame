package com.example.denquizgame.stats

import com.example.denquizgame.IntCache


interface StatsRepository {

    fun stats(): Pair<Int, Int>
    fun clear()

    class Base(
        private val corrects: IntCache,
        private val incorrects: IntCache
    ) : StatsRepository {
        override fun stats(): Pair<Int, Int> {
            return Pair(corrects.read(), incorrects.read())
        }

        override fun clear() {
            corrects.save(0)
            incorrects.save(0)
        }
    }

}