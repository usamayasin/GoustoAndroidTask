package com.app.goustotask.data.usecase

import com.app.goustotask.testUtil.MockTestUtil
import com.app.goustotask.data.local.repository.AbstractLocalRepository
import com.app.goustotask.data.model.ProductDTO
import com.app.goustotask.data.remote.DataState
import com.app.goustotask.data.repository.Repository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class FetchProductsUsecaseTest {

    @MockK
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking FetchProductsUseCase gives products`() = runBlocking {
        // Given
        val fetchProductsInfoUseCase = FetchProductsUsecase(repository)
        val mockProducts = MockTestUtil.getMockProducts(5)

        // When
        coEvery { repository.getProducts(750) }
            .returns(flowOf(DataState.Success<ProductDTO>(mockProducts)))

        // Invoke
        val userCaseResponse = fetchProductsInfoUseCase(750)

        // Then
        MatcherAssert.assertThat(userCaseResponse, CoreMatchers.notNullValue())

        val productsListDataState = userCaseResponse.first()
        MatcherAssert.assertThat(productsListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(productsListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val productsList = (productsListDataState as DataState.Success).data
        MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
        Assert.assertEquals(productsList!!.size, mockProducts.data.size)

    }

    @Test
    fun `test invoking FetchProductsUseCase gives error`() = runBlocking {
        // Given
        val fetchProductsInfoUseCase = FetchProductsUsecase(repository)
        val mockResponse = MockTestUtil.getMockProductsError()

        // When
        coEvery { repository.getProducts(750) }
            .returns(flowOf(DataState.Error<ProductDTO>(mockResponse)))

        // Invoke
        val userCaseResponse = fetchProductsInfoUseCase(750)

        // Then
        MatcherAssert.assertThat(userCaseResponse, CoreMatchers.notNullValue())

        val productsListDataState = userCaseResponse.first()
        MatcherAssert.assertThat(productsListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(productsListDataState, CoreMatchers.instanceOf(DataState.Error::class.java))

        val errorResponse = (productsListDataState as DataState.Error).error
        Assert.assertEquals(errorResponse, mockResponse)

    }
}