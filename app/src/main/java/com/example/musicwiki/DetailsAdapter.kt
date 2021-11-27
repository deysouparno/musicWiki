package com.example.musicwiki

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.musicwiki.databinding.ArtistItemBinding
import com.example.musicwiki.databinding.DetailsItemBinding
import com.example.musicwiki.models.Album
import com.example.musicwiki.models.ArtistDetails
import com.example.musicwiki.models.Track

class AlbumsAdapter : ListAdapter<Album, AlbumsViewHolder>(albumDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        return AlbumsViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class AlbumsViewHolder(private val binding: DetailsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun form(parent: ViewGroup): AlbumsViewHolder {
            return AlbumsViewHolder(
                DetailsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            );
        }
    }

    fun bind(album: Album) {
        binding.apply {
            Glide.with(itemImage.context)
                .load(album.image.last().text)
                .error(R.drawable.ic_baseline_image_24)
                .into(itemImage)

            itemName.text = album.name
            itemSub.text = album.artist.name
        }
    }

}

class ArtistsAdapter : ListAdapter<ArtistDetails, ArtistsViewHolder>(artistDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistsViewHolder {
        return ArtistsViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        holder.bind(artist = getItem(position))
    }

}

class ArtistsViewHolder(private val binding: ArtistItemBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun form(parent: ViewGroup): ArtistsViewHolder {
            return ArtistsViewHolder(
                ArtistItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(artist: ArtistDetails) {
        binding.apply {
            Glide.with(artistImage.context)
                .load(artist.image.last().text)
                .error(R.drawable.ic_baseline_image_24)
                .into(artistImage)

            artistName.text = artist.name
        }
    }
}

class TracksAdapter : ListAdapter<Track, TracksViewHolder>(trackDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TracksViewHolder {
        return TracksViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: TracksViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class TracksViewHolder(private val binding: DetailsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun form(parent: ViewGroup): TracksViewHolder {
            return TracksViewHolder(
                DetailsItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(track: Track) {
        binding.apply {
            Glide.with(itemImage.context)
                .load(track.image.last().text)
                .error(R.drawable.ic_baseline_image_24)
                .into(itemImage)

            itemName.text = track.name
            itemSub.text = track.artist.name
        }
    }

}

private val albumDiffCallback = object : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}

private val artistDiffCallback = object : DiffUtil.ItemCallback<ArtistDetails>() {
    override fun areItemsTheSame(oldItem: ArtistDetails, newItem: ArtistDetails): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: ArtistDetails, newItem: ArtistDetails): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}


private val trackDiffCallback = object : DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.mbid == newItem.mbid
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

}


