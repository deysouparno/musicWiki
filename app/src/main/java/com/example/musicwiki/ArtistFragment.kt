package com.example.musicwiki

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.musicwiki.databinding.FragmentListBinding

class ArtistFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var adapter: ArtistsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater)

        adapter = ArtistsAdapter()

        binding.listRv.apply {
            adapter = adapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        viewModel.artists.observe(viewLifecycleOwner, {
            Log.d("artist", "artists size ${it.size}")
            adapter.submitList(it)
        })
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}