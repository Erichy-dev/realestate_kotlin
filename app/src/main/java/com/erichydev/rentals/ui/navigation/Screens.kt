package com.erichydev.rentals.ui.navigation

sealed class Screens(val route: String) {
    data object HomeScreen : Screens("home")

    data object PlotScreen: Screens("plot/{plotId}"){
        fun passPlotId(plotId: String):String {
            return "plot/$plotId"
        }
    }
}