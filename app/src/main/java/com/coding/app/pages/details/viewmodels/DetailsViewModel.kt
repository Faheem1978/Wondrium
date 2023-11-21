package com.coding.app.pages.details.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coding.app.common.networking.retrofit.NetworkResult
import com.coding.app.pages.details.models.DetailsResponseModel
import com.coding.app.pages.details.models.ProductDetailsResponseModel
import com.coding.app.pages.details.repository.DetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: DetailsRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _response: MutableLiveData<NetworkResult<DetailsResponseModel>> =
        MutableLiveData()
    val response: LiveData<NetworkResult<DetailsResponseModel>> = _response


    fun fetchDetails(name:String) = viewModelScope.launch {
        repository.getDetails(name).collect { values ->
            _response.value = values
        }
    }

}