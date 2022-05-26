package com.app.goustotask.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.goustotask.base.BaseViewModel
import com.app.goustotask.data.model.ProductsDomainModel
import com.app.goustotask.data.remote.DataState
import com.app.goustotask.data.usecase.FetchProductsUsecase
import com.app.goustotask.data.usecase.FilterProductsUsecase
import com.app.goustotask.data.usecase.GetScreenSizeInfoUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel @Inject constructor(
    private val fetchProductsUsecase: FetchProductsUsecase,
    private val getScreenSizeInfoUsecase: GetScreenSizeInfoUsecase,
    private val filterProductsUsecase: FilterProductsUsecase
) : BaseViewModel() {

    private val deviceWidth = getScreenSizeInfoUsecase.getScreenDisplayMetric().widthPixels

    private val _productsList = MutableLiveData<List<ProductsDomainModel>>()
    val productsLiveData: LiveData<List<ProductsDomainModel>> = _productsList

    private val _filteredProductsList = MutableLiveData<ArrayList<ProductsDomainModel>>()
    val filteredProductsLiveData: LiveData<ArrayList<ProductsDomainModel>> = _filteredProductsList


    init {
        fetchProducts(deviceWidth)
    }

    private fun fetchProducts(deviceWidth: Int) {
        showLoader()
        viewModelScope.launch(Dispatchers.IO) {
            fetchProductsUsecase.invoke(deviceWidth = deviceWidth).collect { dataState ->
                withContext(Dispatchers.Main) {
                    hideLoading()
                    when (dataState) {
                        is DataState.Success -> {
                            _productsList.postValue(dataState.data!!)
                        }
                        is DataState.Error -> {
                            onResponseComplete(dataState.error)
                        }
                    }
                }
            }
        }
    }

    fun getProductsList() = _productsList.value as ArrayList


    fun filterProductsList(
        searchString: String,
        productsList: ArrayList<ProductsDomainModel>
    ) {
        showLoader()
        viewModelScope.launch(Dispatchers.IO) {
            filterProductsUsecase.invoke(searchString = searchString, productsList = productsList)
                .collect { dataState ->
                    withContext(Dispatchers.Main) {
                        hideLoading()
                        when (dataState) {
                            is DataState.Success -> {
                                _filteredProductsList.postValue(dataState.data!!)
                            }
                            is DataState.Error -> {
                                onResponseComplete(dataState.error)
                            }
                        }
                    }
                }
        }
    }

}
