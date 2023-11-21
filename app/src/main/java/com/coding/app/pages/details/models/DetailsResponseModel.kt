package com.coding.app.pages.details.models

import com.google.gson.annotations.SerializedName


data class DetailsResponseModel (

  @SerializedName("message" ) var message : ArrayList<String> = arrayListOf(),
  @SerializedName("status"  ) var status  : String?           = null

)