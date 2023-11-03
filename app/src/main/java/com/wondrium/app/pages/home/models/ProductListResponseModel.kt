package com.wondrium.app.pages.home.models

import com.google.gson.annotations.SerializedName


data class ProductListResponseModel (

  @SerializedName("products" ) var products : ArrayList<Products> = arrayListOf()

)