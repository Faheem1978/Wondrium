package com.coding.app.pages.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coding.app.common.networking.retrofit.NetworkResult
import com.coding.app.common.networking.retrofit.Status
import com.coding.app.pages.home.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    application: Application
) : AndroidViewModel(application) {


    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status> = _status


    private val _response: MutableLiveData<List<String>> = MutableLiveData()
    val response: LiveData<List<String>> = _response


    fun fetchList() = viewModelScope.launch {
        repository.getList().transform {
            when (it) {
                is NetworkResult.Success -> {
                    val temp = ArrayList<String>()
                    it.data?.message?.let { message ->
                        for (item in message.toList()) {
//                            val subCategory = item.second
//                            if (subCategory.size > 0) {
//                                for (sub in subCategory) {
//                                    temp.add("${item.first} $sub")
//                                }
//                            } else {
                            val str = item.first
                            if (str.length != 1) {
                                temp.add(str[0].uppercase() + str.substring(1))
                            } else {
                                temp.add(str.uppercase())
                            }
//                            }
                        }
                    }
                    temp.sort()
                    emit(temp)
                    _status.value = Status.SUCCESS
                }

                is NetworkResult.Error -> {
                    emit(emptyList())
                    _status.value = Status.ERROR

                }

                is NetworkResult.Loading -> {
                    emit(emptyList())
                    _status.value = Status.LOADING

                }
            }
        }.collect { values ->
            _response.value = values
        }
    }

}