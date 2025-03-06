package com.example.denquizgame.views.question

import android.content.Context
import android.util.AttributeSet

class QuestionTextView : androidx.appcompat.widget.AppCompatTextView, UpdateText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun updateText(text: String) {
        this.text = text
    }

    override fun getFreezesText() = true
}

interface UpdateText {

    fun updateText(text: String)
}