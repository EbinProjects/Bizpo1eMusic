package com.example.bibzpole.Interfaces

import com.example.bibzpole.dataModel.ResponseLists
import com.example.bibzpole.fileUtils.Constants
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicApi {

    @GET("search")
    suspend fun search(@Query("term") term: String): Response<ResponseLists?>?


    companion object {
        fun create(): MusicApi {
            val logger =
                HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            val client: OkHttpClient =
                OkHttpClient.Builder().addInterceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .build()
                    chain.proceed(newRequest)
                }.addInterceptor(logger)
                    .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MusicApi::class.java)
        }
    }

}