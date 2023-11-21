package com.coding.app.pages.details.repository

import com.coding.app.common.networking.BaseDataSource
import com.coding.app.common.networking.retrofit.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class DetailsRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    BaseDataSource() {

    suspend fun getDetails(name: String) = flow {
        emit(getResult { remoteDataSource.getDetails(name) })
    }
}