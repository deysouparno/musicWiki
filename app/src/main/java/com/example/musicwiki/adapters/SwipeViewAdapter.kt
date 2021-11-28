package com.example.musicwiki.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.musicwiki.screens.AlbumFragment
import com.example.musicwiki.screens.ArtistFragment
import com.example.musicwiki.screens.TrackFragment

class SwipeViewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AlbumFragment()
            1 -> ArtistFragment()
            else -> TrackFragment()
        }
    }
}