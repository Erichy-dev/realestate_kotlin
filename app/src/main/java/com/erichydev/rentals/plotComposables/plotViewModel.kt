package com.erichydev.rentals.plotComposables

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erichydev.rentals.api.getPlot
import com.erichydev.rentals.api.getPlotCaretakers
import com.erichydev.rentals.api.getPlotPics
import com.erichydev.rentals.data.Plot
import com.erichydev.rentals.data.PlotOccupant
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

    // plot details
    private val _plotDetails = MutableLiveData<Plot>()
    val plotDetails: LiveData<Plot> = _plotDetails

    private fun setPlotDetails(plot: Plot) {
        _plotDetails.postValue(plot)
    }

    fun fetchPlotDetails(context: Context, plotNumber: String) {
        getPlot(
            context,
            plotNumber,
            onSuccess = { plotResponse ->
                setPlotDetails(plotResponse.plot)
            },
            onFailure = {
                // handle failure
            }
        )
    }

    // plot caretakers
    private val _plotCaretakers = MutableLiveData(emptyList<PlotOccupant>())
    val plotCaretakers: LiveData<List<PlotOccupant>> = _plotCaretakers

    private fun setPlotCaretakers(caretakers: List<PlotOccupant>) {
        _plotCaretakers.postValue(caretakers)
    }

    fun fetchPlotCaretakers(context: Context, plotNumber: String) {
        getPlotCaretakers(
            context,
            plotNumber,
            onSuccess = { plotResponse ->
                setPlotCaretakers(plotResponse.caretakers)
            },
            onFailure = {
                // handle failure
            }
        )
    }
}