package com.example.denquizgame.views.choice

import java.io.Serializable

interface ChoiceUiState : Serializable {
    //fun update(button: AppCompatButton)

    fun update(update: UpdateChoiceButton)


    abstract class Abstract(
        //private val value: String,
        private val color: String,
        private val clickable: Boolean = false,
        private val enabled: Boolean = true
    ) : ChoiceUiState {
        /*override fun update(button: AppCompatButton) = with(button) {
            text = value
            if (enabled)
                setBackgroundColor(Color.parseColor(color))
            isEnabled = enabled
            isClickable = clickable

            setBackgroundColor(Color.parseColor(color))
        }*/

        override fun update(update: UpdateChoiceButton) {
            //update.updateText(value)
            update.updateProperties(color, clickable, enabled)
        }
    }


    data class Initial(private val text: String) : Abstract("#62A1EC", true) {
        override fun update(update: UpdateChoiceButton) {
            super.update(update)
            update.updateText(text)
        }
    }

    object AvailableToChoose : Abstract("#62A1EC", true)

    object NotAvailableToChoose : Abstract("#75797E", enabled = false)

    object Correct : Abstract("#5DE85A")

    object Incorrect : Abstract("#E85A5A")

}
