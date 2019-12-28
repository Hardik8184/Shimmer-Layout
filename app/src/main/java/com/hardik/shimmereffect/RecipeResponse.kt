package com.hardik.shimmereffect

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class RecipeResponse : Serializable {

  @SerializedName("id")
  var id: String? = ""

  @SerializedName("description")
  var description: String? = ""

  @SerializedName("name")
  var name: String? = ""

  @SerializedName("price")
  var price :Double?= 0.0

  @SerializedName("thumbnail")
  var thumbnail: String? = ""

  @SerializedName("chef")
  var chef: String? = ""

  @SerializedName("timestamp")
  var timestamp: String? = ""

}