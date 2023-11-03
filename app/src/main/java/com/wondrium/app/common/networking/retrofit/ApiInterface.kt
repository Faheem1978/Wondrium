package com.wondrium.app.common.networking.retrofit

import com.wondrium.app.pages.details.models.ProductDetailsResponseModel
import com.wondrium.app.pages.home.models.ProductListResponseModel
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    @GET("homeitems")
    suspend fun getList(): Response<ProductListResponseModel>
    @GET("details")
    suspend fun getDetails(): Response<ProductDetailsResponseModel>
}