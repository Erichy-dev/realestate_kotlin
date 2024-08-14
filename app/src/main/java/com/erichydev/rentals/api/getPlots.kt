package com.erichydev.rentals.api

import android.content.Context
import com.erichydev.rentals.data.FailedRequest
import com.erichydev.rentals.data.PlotsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

fun getPlots(
    context: Context,
    onSuccess: (PlotsResponse) -> Unit,
    onFailure: () -> Unit
) {
    try {
        if(!isNetworkAvailable(context)) {
            throw Exception("Non internet connection")
        } else {
            apiService.getPlots().enqueue(object: Callback<PlotsResponse> {
                override fun onResponse(
                    call: Call<PlotsResponse>,
                    response: Response<PlotsResponse>
                ) {
                    if(response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        onFailure()
                        failedRequestLiveData.postValue(FailedRequest("Failed to get plots: ${response.message()}"))
                    }
                }

                override fun onFailure(call: Call<PlotsResponse>, t: Throwable) {
                    onFailure()
                    failedRequestLiveData.postValue(FailedRequest("Failed to get plots"))
                    t.printStackTrace()
                }
            })
        }
    } catch (e: Exception) {
        onFailure()
        failedRequestLiveData.postValue(FailedRequest(e.message.toString()))
        e.printStackTrace()
    }
}