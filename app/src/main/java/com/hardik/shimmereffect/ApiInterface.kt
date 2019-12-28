package com.hardik.shimmereffect

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

  @GET("menu.php")
  fun fetchRecipes(): Call<ArrayList<RecipeResponse>>
}