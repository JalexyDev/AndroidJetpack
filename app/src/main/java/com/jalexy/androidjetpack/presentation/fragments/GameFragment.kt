package com.jalexy.androidjetpack.presentation.fragments

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jalexy.androidjetpack.databinding.FragmentGameBinding
import com.jalexy.androidjetpack.domain.models.GameResult
import com.jalexy.androidjetpack.presentation.viewmodels.GameViewModel
import com.jalexy.androidjetpack.presentation.viewmodels.GameViewModelFactory

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding is null")

    private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        GameViewModelFactory(requireActivity().application, args.level)
    }

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.option1Tv)
            add(binding.option2Tv)
            add(binding.option3Tv)
            add(binding.option4Tv)
            add(binding.option5Tv)
            add(binding.option6Tv)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setOptionsClickListeners()
    }

    private fun observeViewModel() {
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.timerTv.text = it
        }
        viewModel.question.observe(viewLifecycleOwner) {
            binding.sumTv.text = it.sum.toString()
            binding.leftNumberTv.text = it.visibleNumber.toString()

            for(i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            binding.answersProgressTv.text = it
        }
        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
            binding.answersProgressTv.setTextColor(getColorByState(it))
        }
        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            startGameFinishedFragment(it)
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if(goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun setOptionsClickListeners() {
        for(optionTv in tvOptions) {
            optionTv.setOnClickListener {
                viewModel.chooseAnswer(optionTv.text.toString().toInt())
            }
        }
    }

    private fun startGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(
            GameFragmentDirections
                .actionGameFragmentToGameFinishedFragment(gameResult)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}