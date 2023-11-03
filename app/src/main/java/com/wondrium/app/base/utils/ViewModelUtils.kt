package com.wondrium.app.base.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewmodel.CreationExtras
import com.wondrium.app.common.networking.retrofit.Resource
import kotlinx.coroutines.Dispatchers


fun <T> getNetworkData(apiFun: suspend () -> Resource<T>) = liveData(Dispatchers.IO) {
    this.emit(Resource.loading(data = null))
    try {
        this.emit(Resource.success(data = apiFun()))
    } catch (exception: Exception) {
        this.emit(
            Resource.error(
                data = null,
                message = exception.message ?: "Something went wrong"
            )
        )
    }
}


inline fun getFactory(crossinline factoryFunction: () -> ViewModel): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            return factoryFunction() as T
        }
    }
}