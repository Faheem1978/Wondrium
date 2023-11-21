package com.coding.app.common.networking

import com.coding.app.common.networking.retrofit.NetworkResult
import retrofit2.Response


abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return NetworkResult.Success(body)
            }

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }

        return error("Error")
    }

    private fun <T> error(message: String): NetworkResult<T> {
        return NetworkResult.Error(message)
    }
}