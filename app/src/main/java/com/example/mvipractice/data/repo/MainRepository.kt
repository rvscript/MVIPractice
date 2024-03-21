package com.example.mvipractice.data.repo

import com.example.mvipractice.data.network.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getPhotos() = apiHelper.getPhotos()
}