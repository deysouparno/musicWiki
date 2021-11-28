package com.example.musicwiki.screens

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.musicwiki.MainViewModel
import com.example.musicwiki.R
import com.example.musicwiki.adapters.SwipeViewAdapter
import com.example.musicwiki.databinding.FragmentDetailsBinding
import com.google.android.material.appbar.AppBarLayout
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
                    binding.tagDetails.text = it.wiki.summary
                }
            })
        }

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.detailsToolbar)
        binding.collapsingToolbar.apply {
            title = tag.name
            expandedTitleGravity = Gravity.TOP
            setExpandedTitleTextAppearance(R.style.expanded_text_style)
            setCollapsedTitleTextAppearance(R.style.collapsed_text_style)
//            setContentScrimColor(R.color.chip_color)
        }

        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            binding.tagDetails.alpha =
                (appBarLayout.totalScrollRange + verticalOffset).toFloat() / appBarLayout.totalScrollRange
        })
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

