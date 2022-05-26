package com.app.goustotask.data.model

import com.app.goustotask.data.local.models.ProductsEntity
import com.google.gson.annotations.SerializedName
import java.io.BufferedInputStream
import java.net.URL


data class ProductDTO(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("data")
    var data: ArrayList<Data> = arrayListOf()
)

data class Data(
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("list_price")
    var price: String? = null,
    @SerializedName("volume")
    var volume: Int? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("zone")
    var zone: String? = null,
    @SerializedName("age_restricted")
    var ageRestricted: Boolean? = null,
    @SerializedName("is_for_sale")
    var isForSale: Boolean? = null,
    @SerializedName("categories")
    var categories: ArrayList<Category> = arrayListOf(),
    @SerializedName("images")
    var images: Map<String, MyImage>? = null

)

data class MyImage(
    @SerializedName("src")
    val src: String? = null,
    @SerializedName("url")
    val url: String? = null
)


fun ProductDTO.toDataBaseModelMapper(): List<ProductsEntity> {
    val productsList: MutableList<ProductsEntity> = ArrayList()
    data.map { product ->

        ProductsEntity(
            id = product.id,
            title = product.title,
            price = product.price,
            volume = product.volume,
            description = product.description,
            zone = product.zone,
            ageRestricted = product.ageRestricted,
            isForSale = product.isForSale,
            imageUrl = product.images?.entries?.first()?.value?.src,
            category = product.categories.filter {
                it.title!!.isNotEmpty()
            }.map {
                it.title.toString()
            }.joinToString(",")
        )
    }.run {
        productsList.addAll(this)
    }
    return productsList
}