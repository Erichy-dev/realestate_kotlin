package com.erichydev.rentals.data

import com.google.gson.annotations.SerializedName

data class Plot(
    @SerializedName("plot_number") val plotNumber: String,
    @SerializedName("plot_upload_data") val plotUploadData: String,
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

data class PlotsResponse(
    @SerializedName("plots") val plots: List<Plot>
)
data class PlotResponse(
    @SerializedName("plot") val plot: Plot
)

data class PlotPic(
    @SerializedName("plot_number") val plotNumber: String,
    @SerializedName("plot_pic") val plotPic: String,
    @SerializedName("plot_pic_desc") val plotPicDesc: String?
)
data class PlotPicResponse (
    @SerializedName("plot_pics") val plotPics: List<PlotPic>
)

data class PlotOccupant(
    @SerializedName("plot_number") val plotNumber: String,
    @SerializedName("plot_occupant_id") val plotOccupantId: String,
    @SerializedName("plot_occupant_f_name") val plotOccupantFName: String,
    @SerializedName("plot_occupant_l_name") val plotOccupantLName: String?,
    @SerializedName("plot_occupant_class") val plotOccupantClass: String,
    @SerializedName("plot_occupant_phone") val plotOccupantPhone: String,
    @SerializedName("plot_occupant_email") val plotOccupantEmail: String?
)
data class PlotCaretakerResponse (
    @SerializedName("caretakers") val caretakers: List<PlotOccupant>
)

data class FailedRequest(
    val error: String
)