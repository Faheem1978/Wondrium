package com.wondrium.app.pages.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wondrium.app.common.networking.retrofit.NetworkResult
import com.wondrium.app.pages.home.models.ProductListResponseModel
import com.wondrium.app.pages.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ProductListResponseModel>> =
        MutableLiveData()
    val response: LiveData<NetworkResult<ProductListResponseModel>> = _response


    fun fetchList() = viewModelScope.launch {
        repository.getList().collect { values ->
            _response.value = values
        }
    }

}