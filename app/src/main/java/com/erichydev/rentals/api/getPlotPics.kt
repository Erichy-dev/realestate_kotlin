package com.erichydev.rentals.api

import android.content.Context
import com.erichydev.rentals.data.FailedRequest
import com.erichydev.rentals.data.PlotPicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getPlotPics(
    context: Context,
    plotNumber: String,
    onSuccess: (PlotPicResponse) -> Unit,
    onFailure: () -> Unit
) {
    try {
        if (!isNetworkAvailable(context)) {
            throw Exception("No internet connection")
        } else {
            apiService.getPlotPics(plotNumber).enqueue(object : Callback<PlotPicResponse> {
                override fun onResponse(
                    call: Call<PlotPicResponse>,
                    response: Response<PlotPicResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        onFailure()
                        failedRequestLiveData.postValue(FailedRequest("Failed to get plot pics: ${response.message()}"))
                    }
                }

                override fun onFailure(call: Call<PlotPicResponse>, t: Throwable) {
                    onFailure()
                    failedRequestLiveData.postValue(FailedRequest("Failed to get plot pics"))
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