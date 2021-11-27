package com.example.musicwiki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.databinding.FragmentListBinding

class TrackFragment() : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var adapter: TracksAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)

        adapter = TracksAdapter()

        binding.listRv.apply {
            adapter = adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.tracks.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}