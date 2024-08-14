package com.erichydev.rentals.plotComposables

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erichydev.rentals.api.getPlotPics
import com.erichydev.rentals.data.PlotPic

class PlotViewModel: ViewModel() {
    // plot pics
    private val _plotPics = MutableLiveData(emptyList<PlotPic>())
    val plotPics: LiveData<List<PlotPic>> = _plotPics

    private fun setPlotPics(pics: List<PlotPic>) {
        _plotPics.postValue(pics)
    }

    fun fetchPlotPics(context: Context, plotNumber: String) {
        getPlotPics(
            context,
            plotNumber,
            onSuccess = { plotPicResponse ->
                setPlotPics(plotPicResponse.plotPics)
            },
            onFailure = {
                // handle failure
            }
        )
    }

    // confirm all images loaded
    private val _imagesLoaded = MutableLiveData(0)
    val imagesLoaded: LiveData<Int> = _imagesLoaded

    fun setImagesLoaded(loaded: Int) {
        _imagesLoaded.postValue(loaded)
    }
}