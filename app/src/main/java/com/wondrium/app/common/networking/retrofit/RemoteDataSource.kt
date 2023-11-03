package com.wondrium.app.common.networking.retrofit

import com.wondrium.app.common.networking.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiInterface: ApiInterface) : BaseDataSource() {
    suspend fun getAllproductList() =  apiInterface.getList()
    suspend fun getDetails() =  apiInterface.getDetails()
}


