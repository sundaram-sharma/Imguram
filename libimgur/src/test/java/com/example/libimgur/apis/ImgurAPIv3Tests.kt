package com.example.libimgur.apis

import junit.framework.Assert.assertNotNull
import okhttp3.OkHttpClient
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ImgurAPIv3Tests {

    //interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", "Client-ID f3e8795f564c3d2")
                .build()
            it.proceed(request)
        }
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.imgur.com/3/").build()
    private val api = retrofit.create(ImgurAPIv3::class.java)

    @Test
    fun getTagsWorking(){


        val response = api.getTags().execute()
        println(response.raw().toString())
        assertNotNull(response.body())
    }

}