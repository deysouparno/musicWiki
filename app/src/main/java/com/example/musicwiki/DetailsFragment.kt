package com.example.musicwiki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicwiki.databinding.FragmentDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator


class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater)

        val args: DetailsFragmentArgs by navArgs()

        val tag = args.tag

        viewModel.apply {
            getTagDetails(tag = tag.name)
            getAlbums(tag = tag.name)
            getArtists(tag = tag.name)
            getTracks(tag = tag.name)

            tagDetails.observe(viewLifecycleOwner, {
                it?.let {
                    binding.tagName.text = it.name
                    binding.tagDetails.text = it.wiki.summary
                }
            })
        }

        binding.apply {
            tagName.text = tag.name
        }

        val tabLayout = binding.tabLayout
        binding.viewPager.adapter = SwipeViewAdapter(this)
        TabLayoutMediator(tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Albums"
                }
                1 -> {
                    tab.text = "Artists"
                }
                2 -> {
                    tab.text = "Tracks"
                }
            }
        }.attach()


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}

class SwipeViewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            1 -> AlbumFragment()
            2 -> ArtistFragment()
            else -> TrackFragment()
        }
    }
}