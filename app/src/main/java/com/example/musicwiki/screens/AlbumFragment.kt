package com.example.musicwiki.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.MainViewModel
import com.example.musicwiki.adapters.AlbumsAdapter
import com.example.musicwiki.databinding.DetailsListBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AlbumFragment : Fragment() {

    private var _binding: DetailsListBinding? = null
    private val binding: DetailsListBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: AlbumsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsListBinding.inflate(inflater)

        adapter = AlbumsAdapter()
        binding.listRv.adapter = adapter
        binding.listRv.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.albums.observe(viewLifecycleOwner, {
            adapter.submitList(it)
            binding.shimmer.isVisible = it.isEmpty()
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}