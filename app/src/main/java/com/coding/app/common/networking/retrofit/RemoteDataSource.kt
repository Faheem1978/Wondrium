package com.coding.app.common.networking.retrofit

import com.coding.app.common.networking.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) : BaseDataSource() {
    suspend fun getAllItemList() =  apiInterface.getListOfBreeds()
    suspend fun getDetails(name: String) =  apiInterface.getDetails(name)
}


