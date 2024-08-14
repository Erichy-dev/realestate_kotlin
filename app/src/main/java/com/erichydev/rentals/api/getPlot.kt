package com.erichydev.rentals.api

import android.content.Context
import com.erichydev.rentals.data.FailedRequest
import com.erichydev.rentals.data.PlotResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getPlot(
    context: Context,
    plotNumber: String,
    onSuccess: (PlotResponse) -> Unit,
    onFailure: () -> Unit
) {
    try {
        if (!isNetworkAvailable(context)) {
            throw Exception("No internet connection")
        } else {
            apiService.getPlot(plotNumber).enqueue(object : Callback<PlotResponse> {
                override fun onResponse(
                    call: Call<PlotResponse>,
                    response: Response<PlotResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        onFailure()
                        failedRequestLiveData.postValue(FailedRequest("Failed to get plot: ${response.message()}"))
                    }
                }

                override fun onFailure(call: Call<PlotResponse>, t: Throwable) {
                    onFailure()
                    failedRequestLiveData.postValue(FailedRequest("Failed to get plot"))
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