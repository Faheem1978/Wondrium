package com.coding.app.common.networking.retrofit

import com.coding.app.pages.details.models.DetailsResponseModel
import com.coding.app.pages.home.models.ListBreedresponseModel
import retrofit2.Response
import retrofit2.http.*


interface ApiInterface {

    @GET("breeds/list/all")
    suspend fun getListOfBreeds(): Response<ListBreedresponseModel>

    @GET("breed/{name}/images")
    suspend fun getDetails(@Path("name") name: String): Response<DetailsResponseModel>

}