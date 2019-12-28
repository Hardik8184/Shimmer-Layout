package com.hardik.shimmereffect

import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

  private const val BASE_URL = "https://api.androidhive.info/json/shimmer/"

  private var retrofit: Retrofit? = null

  val client: Retrofit?
    get() {
      if (retrofit == null) {
        retrofit = Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
      }
      return retrofit
    }
}