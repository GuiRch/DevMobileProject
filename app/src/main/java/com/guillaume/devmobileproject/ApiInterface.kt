package com.guillaume.devmobileproject

import com.squareup.moshi.JsonClass
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("photos")
    fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") pageLimit: Int,
        @Query("order_by") order: String
    ) : Call<MutableList<Photo>>

}

@JsonClass(generateAdapter = true)
data class Photo (
    val id : String,
    val color: String,
    val description: String,
    val width : String,
    val height: String,
    val url: PhotoUrl,
    val likes: String,
    )

@JsonClass(generateAdapter = true)
data class PhotoUrl (
    val full: String,
    val regular: String,
)

