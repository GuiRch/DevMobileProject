package com.guillaume.devmobileproject


import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
private const val BASE_URL = "https://api.unsplash.com"
private const val API_KEY = ""
object RetrofitInstance {
    //val BASE_URL = "https://api.unsplash.com"
    private val retrofit by lazy {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        //OkhttpClient for building http request url
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(Interceptor {chain: Interceptor.Chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization","Client-ID " + BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            })
            .build()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()
    }
    val api : ApiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }
}