package com.app.goustotask.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.goustotask.data.model.Category
import com.app.goustotask.data.model.Data
import com.app.goustotask.data.model.MyImage
import com.app.goustotask.data.model.ProductDTO
import com.app.goustotask.utils.Constants.TABLE_PRODUCTS

@Entity(tableName = TABLE_PRODUCTS)
data class ProductsEntity(
    @PrimaryKey
    var id: String,
    var title: String? = null,
    var price: String? = null,
    var volume: Int? = null,
    var description: String? = null,
    var zone: String? = null,
    var imageUrl: String? = null,
    var ageRestricted: Boolean? = false,
    var isForSale: Boolean? = true,
    var category: String? = null,
)


fun List<ProductsEntity>.toDTOMapper(): ProductDTO {

    val productDTO = ProductDTO()
    productDTO.status = "ok"

    val dataList: MutableList<Data> = ArrayList()
    this.map { product ->

        val categoryList = arrayListOf<Category>()

        product.category?.let {
            val categories = it.split(",")
            categories.map {
                Category(it)
            }.run {
                categoryList.addAll(this)
            }
        }

        val imagesHashMap = hashMapOf("src" to MyImage(product.imageUrl, product.imageUrl))

        Data(
            id = product.id,
            title = product.title,
            price = product.price,
            volume = product.volume,
            description = product.description,
            zone = product.zone,
            ageRestricted = product.ageRestricted,
            isForSale = product.isForSale,
            categories = categoryList,
            images = imagesHashMap
        )
    }.run {
        dataList.addAll(this)
    }
    productDTO.data = dataList as ArrayList<Data>
    return productDTO
}