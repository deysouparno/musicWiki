package com.example.musicwiki

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicwiki.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {

    val tags = MutableLiveData(emptyList<Tag>())
    val albums = MutableLiveData(emptyList<Album>())
    val artists = MutableLiveData(emptyList<ArtistDetails>())
    val tracks = MutableLiveData(emptyList<Track>())
    val tagDetails = MutableLiveData<TagDetailsWrapper>()

    fun getTags() {
        viewModelScope.launch {
            tags.value = repository.getTags().toptags.tag
        }
    }

    fun getTagDetails(tag: String) {
        viewModelScope.launch {
            tagDetails.value = repository.getTagDetails(tag = tag).tag
        }
    }

    fun getAlbums(tag: String) {
        viewModelScope.launch {
            albums.value = repository.getAlbums(tag = tag).albums.album
        }
    }

    fun getArtists(tag: String) {
        viewModelScope.launch {
            artists.value = repository.getArtists(tag = tag).topartists.artist
        }
    }

    fun getTracks(tag: String) {
        viewModelScope.launch {
            tracks.value = repository.getTracks(tag = tag).tracks.track
        }
    }


}