package com.example.mvipractice.data.network

import com.example.mvipractice.data.model.PhotosItem

interface ApiHelper {
    suspend fun getPhotos(): List<PhotosItem>
}

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override suspend fun getPhotos(): List<PhotosItem> {
        return apiService.getPhotos()
    }
}