package com.app.goustotask.utils

object Constants {

    //NETWORK
    object NETWORK{
        const val PRODUCT_END_POINT = "products"
        const val QUERY_KEY_INCLUDES = "includes[]"
        const val QUERY_KEY_IMAGE_SIZES = "image_sizes[]"
        const val QUERY_KEY_LIMIT = "limit"
        const val QUERY_VALUE_CATEGORIES = "categories"
        const val QUERY_VALUE_LIMIT = "150"
        const val TIME_OUT = 1500L
        const val BASE_URL = "https://api.gousto.co.uk/products/v2/"

    }

    //Constants
    const val DATABASE_NAME = "myDataBase.db"
    const val TABLE_PRODUCTS = "products"
    const val BUNDLE_KEY = "product"

}