package com.example.denquizgame

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton

interface ChoiceUiState {
    fun update(button: AppCompatButton)

    abstract class Abstract(
        private val value: String,
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true
    ) : ChoiceUiState {
        override fun update(button: AppCompatButton) = with(button) {
            text = value
            if (enabled)
                setBackgroundColor(Color.parseColor(color))
            isEnabled = enabled
            isClickable = clickable
        }
    }

    data class NotAvailableToChoose(private val text: String) : Abstract(text, "", enabled = false)

    data class AvailableToChoose(private val text: String) : Abstract(text, "#62A1EC", true)

    data class Correct(private val text: String) : Abstract(text, "#5DE85A")

    data class Incorrect(private val text: String) : Abstract(text, "#E85A5A")

}
