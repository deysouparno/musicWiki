package com.example.musicwiki

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.musicwiki.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!


    private val viewModel: MainViewModel by activityViewModels()

    private var expanded = false
    private var flag = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater)

//        lifecycleScope.launchWhenStarted {
//            viewModel.getTags()
//            loadTags()
//        }
        viewModel.getTags()
        viewModel.tags.observe(viewLifecycleOwner, {

                loadTags()

        })

        binding.expand.setOnClickListener {
            if (expanded) {
                contract()
            } else {
                expand()
            }
        }

        binding.tags


        return binding.root
    }

    private fun loadTags() {
        binding.tags.removeAllViews()
        for (i in viewModel.tags.value!!.indices) {
            if (i == 9) {
                break
            }
            val tag = viewModel.tags.value!![i]
            val chip = Chip(requireContext())
            chip.text = tag.name
            chip.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        tag
                    )
                )
            }
            binding.tags.addView(chip)
        }
        flag = false
    }

    private fun expand() {
        for (i in 9..viewModel.tags.value!!.size) {
            if (i == 30) {
                break
            }
            val tag = viewModel.tags.value!![i]
            val chip = Chip(requireContext())
            chip.text = tag.name
            chip.setOnClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        tag
                    )
                )
            }
            binding.tags.addView(chip)
        }
        binding.expand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        expanded = true
    }

    private fun contract() {
        binding.tags.removeViews(8, 21)
        expanded = false
        binding.expand.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

