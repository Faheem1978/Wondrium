package com.wondrium.app.pages.details.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wondrium.app.common.networking.retrofit.NetworkResult
import com.wondrium.app.pages.details.models.ProductDetailsResponseModel
import com.wondrium.app.pages.details.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<ProductDetailsResponseModel>> =
        MutableLiveData()
    val response: LiveData<NetworkResult<ProductDetailsResponseModel>> = _response


    fun fetchDetails() = viewModelScope.launch {
        repository.getDetails().collect { values ->
            _response.value = values
        }
    }

}