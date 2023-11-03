package com.wondrium.app.pages.home.models

import com.google.gson.annotations.SerializedName


data class Products (

  @SerializedName("product_id"                ) var productId               : Int?     = null,
  @SerializedName("product_type"              ) var productType             : String?  = null,
  @SerializedName("product_sku"               ) var productSku              : String?  = null,
  @SerializedName("product_sort_order"        ) var productSortOrder        : Int?     = null,
  @SerializedName("product_name"              ) var productName             : String?  = null,
  @SerializedName("product_image_name"        ) var productImageName        : String?  = null,
  @SerializedName("product_short_description" ) var productShortDescription : String?  = null,
  @SerializedName("course_id"                 ) var courseId                : Int?     = null,
  @SerializedName("course_name"               ) var courseName              : String?  = null,
  @SerializedName("course_image_name"         ) var courseImageName         : String?  = null,
  @SerializedName("course_has_hd_video"       ) var courseHasHdVideo        : Boolean? = null,
  @SerializedName("course_has_cc_video"       ) var courseHasCcVideo        : Boolean? = null,
  @SerializedName("course_url_key"            ) var courseUrlKey            : String?  = null,
  @SerializedName("lecture_number"            ) var lectureNumber           : Int?     = null,
  @SerializedName("course_swatch_image"       ) var courseSwatchImage       : String?  = null,
  @SerializedName("course_poster_image"       ) var coursePosterImage       : String?  = null,
  @SerializedName("lecture_poster_image"      ) var lecturePosterImage      : String?  = null,
  @SerializedName("content_brand"             ) var contentBrand            : String?  = null,
  @SerializedName("content_partner"           ) var contentPartner          : String?  = null,
  @SerializedName("content_classification"    ) var contentClassification   : String?  = null,
  @SerializedName("content_restriction"       ) var contentRestriction      : String?  = null,
  @SerializedName("content_blacklist"         ) var contentBlacklist        : String?  = null

)