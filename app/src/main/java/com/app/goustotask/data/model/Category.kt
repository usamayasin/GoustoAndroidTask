package com.app.goustotask.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("title")
    var title: String? = null
)