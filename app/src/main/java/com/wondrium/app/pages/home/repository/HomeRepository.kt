package com.wondrium.app.pages.home.repository

import com.wondrium.app.common.networking.BaseDataSource
import com.wondrium.app.common.networking.retrofit.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseDataSource() {

    suspend fun getList() = flow {
        emit(getResult { remoteDataSource.getAllproductList() })
    }

}