package com.jalexy.androidjetpack.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.jalexy.androidjetpack.R
import com.jalexy.androidjetpack.databinding.FragmentGameFinishedBinding
import com.jalexy.androidjetpack.domain.models.GameResult

class GameFinishedFragment : Fragment() {

    companion object {

        private const val KEY_GAME_RESULT = "game_result"

        fun newInstance(result: GameResult): GameFinishedFragment =
            GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, result)
                }
            }
    }

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding is null")

    private lateinit var result: GameResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let{
            result = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        bindView()
    }

    private fun setupClickListeners() {
        requireActivity().onBackPressedDispatcher
            .addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        retryGame()
                    }
                })
        binding.retryBtn.setOnClickListener {
            retryGame()
        }
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager
            .popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun bindView() {
        with(binding) {
            iconResult.setImageResource(getResultImage())
            requiredAnswersTv.text = String.format(
                getString(R.string.result_required_answers_text),
                result.gameSettings.minCountRightAnswers
            )
            scoreAnswersTv.text = String.format(
                getString(R.string.result_score_text),
                result.countRightAnswers
            )
            requiredPercentageTv.text = String.format(
                getString(R.string.result_required_percentage_text),
                result.gameSettings.minPercentRightAnswers
            )
            scorePercentageTv.text = String.format(
                getString(R.string.result_score_text),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getResultImage(): Int =
        if(result.win) {
            R.drawable.ic_check_circle
        } else {
            R.drawable.ic_loose
        }

    private fun getPercentOfRightAnswers() = with(result) {
        if(countQuestions == 0) {
            0
        } else {
            ((countRightAnswers / countQuestions.toDouble()) * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}