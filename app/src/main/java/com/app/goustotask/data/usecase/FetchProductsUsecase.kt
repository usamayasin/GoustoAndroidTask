package com.app.goustotask.data.usecase

import com.app.goustotask.data.model.ProductsDomainModel
import com.app.goustotask.data.model.toDomainModelMapper
import com.app.goustotask.data.remote.DataState
import com.app.goustotask.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchProductsUsecase @Inject constructor(
    private val repository: Repository
) {

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(deviceWidth:Int): Flow<DataState<List<ProductsDomainModel>>> {
        return flow {
            repository.getProducts(deviceWidth = deviceWidth).collect { response ->
                when (response) {
                    is DataState.Success -> {
                        response.data?.let {
                            if(it.data.isNotEmpty()){
                                val result = it.toDomainModelMapper()
                                emit(DataState.Success(result))
                            } else {
                                emit(DataState.Error<List<ProductsDomainModel>>(DataState.CustomMessages.EmptyData))
                            }
                        } ?: run {
                            emit(DataState.Error<List<ProductsDomainModel>>(response.error))
                        }
                    }
                    is DataState.Error -> {
                        emit(DataState.Error<List<ProductsDomainModel>>(response.error))
                    }
                }
            }
        }.flowOn(Dispatchers.IO)
    }
}
