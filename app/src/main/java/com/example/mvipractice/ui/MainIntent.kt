package com.example.mvipractice.ui

import com.example.mvipractice.data.model.PhotosItem

sealed class MainIntent {
    object FetchUser: MainIntent()
}

sealed class MainState {
    object Idle: MainState()
    object Loading: MainState()
    data class Photos(val photos: List<PhotosItem>): MainState()
    data class Error(val error: String?): MainState()
}