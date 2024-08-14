package com.erichydev.rentals.homeComposables

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erichydev.rentals.api.getPlots
import com.erichydev.rentals.data.Plot
import com.erichydev.rentals.data.PlotsResponse

class HomeViewModel : ViewModel() {
    private val _fetchedPlots = MutableLiveData<List<Plot>>(emptyList())
    val fetchedPlots: LiveData<List<Plot>> = _fetchedPlots

    private fun setFetchedPlots(plotsResponse: PlotsResponse) {
        _fetchedPlots.postValue(plotsResponse.plots)
    }

    fun fetchPlots(context: Context) {
        getPlots(
            context,
            {
                plots -> setFetchedPlots(plots)
            },
            {
                // handle error
            }
        )
    }
}