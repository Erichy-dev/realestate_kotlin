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
    private val _originalPlots = MutableLiveData<List<Plot>>(emptyList())
    val originalPlots: LiveData<List<Plot>> = _originalPlots

    fun setFetchedPlots(plots: List<Plot>) {
        _fetchedPlots.postValue(plots)
    }
    private fun setNewPlots(plots: List<Plot>) {
        _fetchedPlots.postValue(plots)
        _originalPlots.postValue(plots)
    }

    fun fetchPlots(context: Context) {
        getPlots(
            context,
            {
                plots -> setNewPlots(plots.plots)
            },
            {
                // handle error
            }
        )
    }

    // room filter
    private val _expandedRooms = MutableLiveData(false)
    val expandedRooms: LiveData<Boolean> = _expandedRooms

    fun setExpandedRooms(expanded: Boolean) {
        _expandedRooms.postValue(expanded)
    }

    private val _selectedRoomOption = MutableLiveData("Bedsitter")
    val selectedRoomOption: LiveData<String> = _selectedRoomOption

    fun setSelectedRoomOption(roomOption: String) {
        _selectedRoomOption.postValue(roomOption)
    }

    // uploaded filter
    private val _uploadedFilter = MutableLiveData(true)
    val uploadedFilter: LiveData<Boolean> = _uploadedFilter

    fun toggleUploadedFilter() {
        _uploadedFilter.postValue(!_uploadedFilter.value!!)
    }

    // price filter
    private val _priceFilter = MutableLiveData(true)
    val priceFilter: LiveData<Boolean> = _priceFilter

    fun togglePriceFilter() {
        _priceFilter.postValue(!_priceFilter.value!!)
    }

    // rating filter
    private val _ratingFilter = MutableLiveData(true)
    val ratingFilter: LiveData<Boolean> = _ratingFilter

    fun toggleRatingFilter() {
        _ratingFilter.postValue(!_ratingFilter.value!!)
    }
}