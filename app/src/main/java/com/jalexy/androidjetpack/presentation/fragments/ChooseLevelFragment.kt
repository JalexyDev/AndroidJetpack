package com.jalexy.androidjetpack.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jalexy.androidjetpack.databinding.FragmentChooseLevelBinding
import com.jalexy.androidjetpack.domain.models.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding is null")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            testLevelBtn.setOnClickListener {
                startGameFragment(Level.TEST)
            }
            easyLevelBtn.setOnClickListener {
                startGameFragment(Level.EASY)
            }
            normalLevelBtn.setOnClickListener {
                startGameFragment(Level.NORMAL)
            }
            hardLevelBtn.setOnClickListener {
                startGameFragment(Level.HARD)
            }
        }
    }

    private fun startGameFragment(level: Level) {
        findNavController().navigate(
            ChooseLevelFragmentDirections
                .actionChooseLevelFragmentToGameFragment(level)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}