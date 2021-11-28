package com.example.musicwiki.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.MainViewModel
import com.example.musicwiki.adapters.TracksAdapter
import com.example.musicwiki.databinding.DetailsListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TrackFragment : Fragment() {

    private var _binding: DetailsListBinding? = null
    private val binding: DetailsListBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var adapter: TracksAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsListBinding.inflate(inflater)

        adapter = TracksAdapter()

        binding.listRv.adapter = adapter
        binding.listRv.layoutManager = GridLayoutManager(requireContext(), 2)


        viewModel.tracks.observe(viewLifecycleOwner, {
            Log.d("artist", "track size ${it.size}")
            adapter.submitList(it)
            if (it.isNotEmpty()) {
                binding.shimmer.isVisible = false
            }
//            adapter.notifyDataSetChanged()
        })


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}