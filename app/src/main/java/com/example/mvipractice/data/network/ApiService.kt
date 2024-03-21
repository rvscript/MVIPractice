package com.example.mvipractice.data.network

import com.example.mvipractice.data.model.PhotosItem
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("/photos")
    suspend fun getPhotos(): List<PhotosItem>
}

object RetrofitBuilder {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}