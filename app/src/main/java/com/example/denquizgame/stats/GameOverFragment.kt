package com.example.denquizgame.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.denquizgame.QuizApp
import com.example.denquizgame.databinding.FragmentGameOverBinding
import com.example.denquizgame.game.NavigateToGame


class GameOverFragment : Fragment() {

    private var _binding: FragmentGameOverBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameOverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel: GameOverViewModel =
            (requireActivity().application as QuizApp).gameOverViewModel


        binding.newGameButton.setOnClickListener {
            (requireActivity() as NavigateToGame).navigateToGame()
        }

        val uiState = viewModel.init(savedInstanceState == null)
        uiState.show(binding.statsTextView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}