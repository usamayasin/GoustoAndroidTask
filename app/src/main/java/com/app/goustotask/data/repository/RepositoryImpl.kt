package com.app.goustotask.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.app.goustotask.MyApplication
import com.app.goustotask.data.local.models.toDTOMapper
import com.app.goustotask.data.local.repository.AbstractLocalRepository
import com.app.goustotask.data.model.ProductDTO
import com.app.goustotask.data.model.toDataBaseModelMapper
import com.app.goustotask.data.remote.*
import com.app.goustotask.utils.GoustoAppUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This is an implementation of [Repository] to handle communication with [ApiService] server.
 */

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val localRepository: AbstractLocalRepository,
    private val context: Context
) : Repository {

    @WorkerThread
    override suspend fun getProducts(deviceWidth:Int): Flow<DataState<ProductDTO>> = flow {

        if (GoustoAppUtils.isInternetAvailable(context)) {
            apiService.getProducts(imageSize = deviceWidth).apply {
                this.onSuccessSuspend({
                    this.data?.let {
                        val productsList = it.toDataBaseModelMapper()
                        localRepository.insertProducts(productsList)
                        emit(DataState.Success(it))
                    }
                }, {
                    emit(this)
                }).onErrorSuspend {
                    emit(error())
                }.onExceptionSuspend {
                    emit(error())
                }
            }
        } else {
            val responseFromLocalDb = localRepository.getAllProducts()
            if (responseFromLocalDb.isNullOrEmpty().not()) {
                val productList = responseFromLocalDb.toDTOMapper()
                emit(DataState.Success(productList))
            } else {
                emit(DataState.Success(ProductDTO()))
            }
        }

    }

}
