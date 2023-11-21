package com.coding.app.pages.home.models

import com.google.gson.annotations.SerializedName


data class ListBreedresponseModel (

  @SerializedName("message" ) var message : Map<String, ArrayList<String>>,

  @SerializedName("status"  ) var status  : String?  = null

)