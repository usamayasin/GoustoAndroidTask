package com.app.goustotask.data.usecase

import com.app.goustotask.data.model.ProductsDomainModel
import com.app.goustotask.data.remote.DataState
import com.app.goustotask.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FilterProductsUsecase @Inject constructor(
    private val repository: Repository
) {

    @ExperimentalCoroutinesApi
    suspend operator fun invoke(
        searchString: String,
        productsList: ArrayList<ProductsDomainModel>
    ): Flow<DataState<ArrayList<ProductsDomainModel>>> {
        return flow {

            val filteredProductsList = arrayListOf<ProductsDomainModel>()
            productsList.filter {
                it.title?.contains(searchString,ignoreCase = true) ?: false
            }.run {
                filteredProductsList.addAll(this)
            }

            when {
                filteredProductsList.isNotEmpty() -> {
                    emit(DataState.Success(filteredProductsList))
                }
                else -> {
                    emit(DataState.Error(DataState.CustomMessages.NotFound))
                }
            }

        }.flowOn(Dispatchers.IO)
    }
}
