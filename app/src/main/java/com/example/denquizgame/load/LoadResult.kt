package com.example.denquizgame.load

interface LoadResult {
    fun isSuccessful(): Boolean
    fun message(): String

    data class Error(private val message: String) : LoadResult {
        override fun isSuccessful() = false

        override fun message() = message

    }

    object Success : LoadResult {
        override fun isSuccessful() = true

        override fun message(): String = throw IllegalStateException("can not happen")

    }

}
