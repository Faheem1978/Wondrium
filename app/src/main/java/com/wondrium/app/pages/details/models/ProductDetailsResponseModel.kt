package com.wondrium.app.pages.details.models

import com.google.gson.annotations.SerializedName


data class ProductDetailsResponseModel (

  @SerializedName("product_sku"                      ) var productSku                   : String?                                 = null,
  @SerializedName("product_magento_id"               ) var productMagentoId             : Int?                                    = null,
  @SerializedName("course_id"                        ) var courseId                     : Int?                                    = null,
  @SerializedName("course_magento_id"                ) var courseMagentoId              : Int?                                    = null,
  @SerializedName("course_name"                      ) var courseName                   : String?                                 = null,
  @SerializedName("course_description"               ) var courseDescription            : String?                                 = null,
  @SerializedName("wondrium_description"             ) var wondriumDescription          : String?                                 = null,
  @SerializedName("course_primary_category"          ) var coursePrimaryCategory        : String?                                 = null,
  @SerializedName("course_image_filename"            ) var courseImageFilename          : String?                                 = null,
  @SerializedName("course_video_filename"            ) var courseVideoFilename          : String?                                 = null,
  @SerializedName("course_soundtrack_filename"       ) var courseSoundtrackFilename     : String?                                 = null,
  @SerializedName("course_guidebook_path"            ) var courseGuidebookPath          : String?                                 = null,
  @SerializedName("course_has_hd_video"              ) var courseHasHdVideo             : Boolean?                                = null,
  @SerializedName("course_has_cc_video"              ) var courseHasCcVideo             : Boolean?                                = null,
  @SerializedName("course_rating"                    ) var courseRating                 : Int?                                    = null,
  @SerializedName("course_professor_has_multiple"    ) var courseProfessorHasMultiple   : Boolean?                                = null,
  @SerializedName("course_professor_product_id_list" ) var courseProfessorProductIdList : ArrayList<CourseProfessorProductIdList> = arrayListOf(),
  @SerializedName("course_professor_name"            ) var courseProfessorName          : String?                                 = null,
  @SerializedName("course_professor_qualification"   ) var courseProfessorQualification : String?                                 = null,
  @SerializedName("course_professor_image_filename"  ) var courseProfessorImageFilename : String?                                 = null,
  @SerializedName("content_classification"           ) var contentClassification        : String?                                 = null,
  @SerializedName("content_restriction"              ) var contentRestriction           : String?                                 = null,
  @SerializedName("content_brand"                    ) var contentBrand                 : String?                                 = null,
  @SerializedName("content_brand_image"              ) var contentBrandImage            : String?                                 = null,
  @SerializedName("content_partner"                  ) var contentPartner               : String?                                 = null,
  @SerializedName("content_partner_image"            ) var contentPartnerImage          : String?                                 = null,
  @SerializedName("course_poster_image"              ) var coursePosterImage            : String?                                 = null,
  @SerializedName("course_swatch_image"              ) var courseSwatchImage            : String?                                 = null,
  @SerializedName("instructor_title"                 ) var instructorTitle              : String?                                 = null,
  @SerializedName("lectures"                         ) var lectures                     : ArrayList<Lectures>                     = arrayListOf()

)