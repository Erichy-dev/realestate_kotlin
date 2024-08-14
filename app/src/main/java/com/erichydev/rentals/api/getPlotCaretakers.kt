package com.erichydev.rentals.api

import android.content.Context
import com.erichydev.rentals.data.FailedRequest
import com.erichydev.rentals.data.PlotCaretakerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun getPlotCaretakers(
    context: Context,
    plotNumber: String,
    onSuccess: (PlotCaretakerResponse) -> Unit,
    onFailure: () -> Unit
) {
    try {
        if (!isNetworkAvailable(context)) {
            throw Exception("No internet connection")
        } else {
            apiService.getPlotCaretakers(plotNumber).enqueue(object : Callback<PlotCaretakerResponse> {
                override fun onResponse(
                    call: Call<PlotCaretakerResponse>,
                    response: Response<PlotCaretakerResponse>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        onFailure()
                        failedRequestLiveData.postValue(FailedRequest("Failed to get plot caretakers: ${response.message()}"))
                    }
                }

                override fun onFailure(call: Call<PlotCaretakerResponse>, t: Throwable) {
                    onFailure()
                    failedRequestLiveData.postValue(FailedRequest("Failed to get plot caretakers"))
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