package com.example.denquizgame.load

interface LoadResult {
    fun isSuccessful(): Boolean
    fun message(): String

    data class Error(private val message: String) : LoadResult {
        override fun isSuccessful(): Boolean {
            return false
        }

        override fun message(): String {
            return message
        }

    }

    object Succses : LoadResult {
        override fun isSuccessful(): Boolean {
            return true
        }

        override fun message(): String = throw IllegalStateException("cannot happen")

    }

}
