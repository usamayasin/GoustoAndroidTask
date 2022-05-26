package com.app.goustotask.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsDomainModel(
    var id: String,
    var title: String? = null,
    var price: String? = null,
    var volume: Int? = null,
    var description: String? = null,
    var zone: String? = null,
    var ageRestricted: Boolean? = null,
    var isForSale: Boolean? = null,
    var category: String? = null,
    var imageUrl: String? = null
):Parcelable


fun ProductDTO.toDomainModelMapper(): List<ProductsDomainModel> {

    val dataList: MutableList<ProductsDomainModel> = ArrayList()
    this.data.map { dataItem ->

        ProductsDomainModel(
            id = dataItem.id,
            title = dataItem.title,
            price = dataItem.price,
            volume = dataItem.volume,
            description = dataItem.description,
            zone = dataItem.zone,
            ageRestricted = dataItem.ageRestricted,
            isForSale = dataItem.isForSale,
            imageUrl = dataItem.images?.entries?.first()?.value?.src,
            category = dataItem.categories.filter {
                it.title!!.isNotEmpty()
            }.map {
                it.title.toString()
            }.joinToString(",")
        )
    }.run {
        dataList.addAll(this)
    }
    return dataList
}