package com.app.goustotask.data.remote

import com.app.goustotask.data.model.ProductDTO
import com.app.goustotask.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.NETWORK.PRODUCT_END_POINT)
    suspend fun getProducts(
        @Query(Constants.NETWORK.QUERY_KEY_INCLUDES) includes: String = Constants.NETWORK.QUERY_VALUE_CATEGORIES,
        @Query(Constants.NETWORK.QUERY_KEY_LIMIT) limit: String = Constants.NETWORK.QUERY_VALUE_LIMIT,
        @Query(Constants.NETWORK.QUERY_KEY_IMAGE_SIZES) imageSize: Int
    ): ApiResponse<ProductDTO>

}
