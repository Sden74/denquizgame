package com.example.denquizgame.views.stats

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import com.example.denquizgame.R
import java.io.Serializable

class StatsTextView : androidx.appcompat.widget.AppCompatTextView, UpdateStats {

    private lateinit var state: StatsUiState

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            val savedState = StatsSavedState(it)
            savedState.save(state)
            return savedState
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val restoredState = state as StatsSavedState
        super.onRestoreInstanceState(restoredState.superState)
        updateOuter(restoredState.restore())
    }

    override fun updateOuter(uiState: StatsUiState) {
        state = uiState
        state.show(this)
    }

    override fun updateInner(corrects: Int, incorrects: Int) {
        //state = StatsUiState(corrects,incorrects)
        setText(resources.getString(R.string.stats, corrects, incorrects))
    }
}

interface StatsUiState : Serializable {

    fun show(statsTextView: UpdateStats)

    class Base(private val corrects: Int, private val incorrects: Int) : StatsUiState {
        override fun show(statsTextView: UpdateStats) {
            statsTextView.updateInner(corrects, incorrects)
        }

    }
}
//data class StatsUiState(val corrects: Int, val incorrects: Int) : Serializable

interface UpdateStats {
    fun updateOuter(uiState: StatsUiState)
    fun updateInner(corrects: Int, incorrects: Int)
}

