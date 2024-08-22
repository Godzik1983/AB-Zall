package com.example.skillbox_hw_quiz.ui.main


import android.animation.AnimatorInflater
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController

import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.FragmentOtvetBinding
import kotlinx.coroutines.launch

class OtvetFragment : Fragment() {

    private var _binding: FragmentOtvetBinding? = null
    private val viewModel by activityViewModels<MainViewModel>()
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtvetBinding.inflate(inflater)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val img = binding.abz
        (AnimatorInflater.loadAnimator(context, R.animator.plavnoe_poyavlenie)as ObjectAnimator)
            .apply {
                target = img
                duration = 3000
                start()
            }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.result
                .flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
                .collect {
                    when (it) {
                        1 -> binding.otvetText.setText(R.string.one)
                        2 -> binding.otvetText.setText(R.string.two)
                        3 -> binding.otvetText.setText(R.string.three)
                        4 -> binding.otvetText.setText(R.string.four)
                        5 -> binding.otvetText.setText(R.string.five)
                        6 -> binding.otvetText.setText(R.string.six)
                        7 -> binding.otvetText.setText(R.string.seven)
                        8 -> binding.otvetText.setText(R.string.eight)
                        9 -> binding.otvetText.setText(R.string.nine)
                    }
                }
        }

        binding.onceAgainButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_otvetFragment_to_ekranOprosa)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}