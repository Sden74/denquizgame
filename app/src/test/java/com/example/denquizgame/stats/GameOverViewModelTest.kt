package com.example.denquizgame.stats

import com.example.denquizgame.views.stats.StatsUiState
import org.junit.Assert.assertEquals
import org.junit.Test

class GameOverViewModelTest {
    @Test
    fun test() {
        val repository = FakeRepository()
        val viewModel = GameOverViewModel(repository = repository)

        assertEquals(StatsUiState.Base(2, 3), viewModel.statsUiState())
        assertEquals(false, repository.clearCalled)

        viewModel.clear()
        assertEquals(true, repository.clearCalled)
    }
}

private class FakeRepository : StatsRepository {

    var clearCalled = false

    override fun stats(): Pair<Int, Int> = Pair(2, 3)
    override fun clear() {
        clearCalled = true
    }

}
