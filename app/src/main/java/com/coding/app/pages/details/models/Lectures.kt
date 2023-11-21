package com.coding.app.pages.details.models

import com.google.gson.annotations.SerializedName


data class Lectures(

    @SerializedName("lecture_number") var lectureNumber: Int? = null,
    @SerializedName("lecture_sku") var lectureSku: String? = null,
    @SerializedName("lecture_magento_id") var lectureMagentoId: Int? = null,
    @SerializedName("lecture_name") var lectureName: String? = null,
    @SerializedName("lecture_description") var lectureDescription: String? = null,
    @SerializedName("lecture_image_filename") var lectureImageFilename: String? = null,
    @SerializedName("lecture_video_filename") var lectureVideoFilename: String? = null,
    @SerializedName("time_in_minutes") var timeInMinutes: String? = null,
    @SerializedName("time_in_seconds") var timeInSeconds: String? = null,
    @SerializedName("content_classification") var contentClassification: String? = null,
    @SerializedName("content_brand") var contentBrand: String? = null,
    @SerializedName("lecture_allow_free_streaming") var lectureAllowFreeStreaming: String? = null

)