package com.example.picsartproject

import com.google.gson.annotations.SerializedName


data class PhotoResponse (
    var status: String = "",
    @SerializedName("response")
    val photooItems: List<PhotoItem> = emptyList()
)

 data class PhotoItem(
         val id: Long? = 0,
         val url: String? = "",
         val title: String? = ""
 )