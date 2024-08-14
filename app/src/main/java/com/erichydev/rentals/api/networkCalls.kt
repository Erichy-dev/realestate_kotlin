package com.erichydev.rentals.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.MutableLiveData
import com.erichydev.rentals.data.FailedRequest
import com.erichydev.rentals.data.PlotCaretakerResponse
import com.erichydev.rentals.data.PlotPicResponse
import com.erichydev.rentals.data.PlotResponse
import com.erichydev.rentals.data.PlotsResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("")
    fun getPlots(): Call<PlotsResponse>

    @GET("plot/{plot_number}/")
    fun getPlot(@Path("plot_number") plot_number: String): Call<PlotResponse>

    @GET("{plot_number}/pics/")
    fun getPlotPics(@Path("plot_number") plot_number: String): Call<PlotPicResponse>

    @GET("{plot_number}/caretakers/")
    fun getPlotCaretakers(@Path("plot_number") plot_number: String): Call<PlotCaretakerResponse>
}

val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
val httpClient = OkHttpClient.Builder().addInterceptor(logging)

val gson: Gson = GsonBuilder()
    .setLenient()
    .create()
val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://realstate.pythonanywhere.com/plots/")
    .client(httpClient.build())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

val failedRequestLiveData = MutableLiveData<FailedRequest>()