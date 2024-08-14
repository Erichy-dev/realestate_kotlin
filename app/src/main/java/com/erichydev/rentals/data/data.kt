package com.erichydev.rentals.data

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class Plot(
    @SerializedName("plot_number") val plotNumber: String,
    @SerializedName("plot_upload_date") val plotUploadDate: String,
    @SerializedName("plot_address") val plotAddress: String,
    @SerializedName("plot_price") val plotPrice: Int,
    @SerializedName("plot_single") val plotSingle: Boolean,
    @SerializedName("plot_bedsitter") val plotBedsitter: Boolean,
    @SerializedName("plot_1B") val plot1B: Boolean,
    @SerializedName("plot_2B") val plot2B: Boolean,
    @SerializedName("plot_3B") val plot3B: Boolean,
    @SerializedName("plot_rating") val plotRating: Int,
    @SerializedName("plot_bg_pic") val plotBgPic: String?,
)

@Immutable
data class PlotsResponse(
    @SerializedName("plots") val plots: List<Plot>
)

@Immutable
data class PlotResponse(
    @SerializedName("plot") val plot: Plot
)

@Immutable
data class PlotPic(
    @SerializedName("plot_number") val plotNumber: String,
    @SerializedName("plot_pic") val plotPic: String,
    @SerializedName("plot_pic_desc") val plotPicDesc: String?
)

@Immutable
data class PlotPicResponse (
    @SerializedName("plot_pics") val plotPics: List<PlotPic>
)

@Immutable
data class PlotOccupant(
    @SerializedName("plot_number") val plotNumber: String,
    @SerializedName("plot_occupant_id") val plotOccupantId: String,
    @SerializedName("plot_occupant_f_name") val plotOccupantFName: String,
    @SerializedName("plot_occupant_l_name") val plotOccupantLName: String?,
    @SerializedName("plot_occupant_class") val plotOccupantClass: String,
    @SerializedName("plot_occupant_phone") val plotOccupantPhone: String,
    @SerializedName("plot_occupant_email") val plotOccupantEmail: String?
)

@Immutable
data class PlotCaretakerResponse (
    @SerializedName("caretakers") val caretakers: List<PlotOccupant>
)

@Immutable
data class FailedRequest(
    val error: String
)
