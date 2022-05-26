package com.app.goustotask.data.repository

import com.app.goustotask.data.model.ProductDTO
import com.app.goustotask.data.remote.DataState
import kotlinx.coroutines.flow.Flow

/**
 * Repository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [RepositoryImpl] for implementation of this class to utilize APIService.
 */

interface Repository {

    suspend fun getProducts(deviceWidth:Int): Flow<DataState<ProductDTO>>
}
