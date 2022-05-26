package com.app.goustotask.data.repository

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.goustotask.data.local.repository.AbstractLocalRepository
import com.app.goustotask.data.remote.ApiService
import com.app.goustotask.data.remote.ApiUtil.successCall
import com.app.goustotask.data.remote.DataState
import com.app.goustotask.testRule.MainCoroutinesRule
import com.app.goustotask.testUtil.MockTestUtil
import com.app.goustotask.utils.GoustoAppUtils
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*


@RunWith(JUnit4::class)
class RepositoryImplTest {

    // Subject under test
    private lateinit var repository: RepositoryImpl

    @MockK
    private lateinit var apiService: ApiService

    @RelaxedMockK
    private lateinit var localRepo: AbstractLocalRepository

    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var context: Context

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        context = mock(Context::class.java)

    }

    @Test
    fun `test getAllProducts() gives list of products`() = runBlocking {

        doReturn(true).`when`(GoustoAppUtils::class.java)
        GoustoAppUtils.isInternetAvailable(context)

        repository = RepositoryImpl(apiService, localRepo, context)
        val mockProducts = MockTestUtil.getMockProducts(5)
        val apiCall = successCall(mockProducts)

        // When
        coEvery { apiService.getProducts(imageSize = 750) }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.getProducts(750)

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val productsListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(productsListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            productsListDataState,
            CoreMatchers.instanceOf(DataState.Success::class.java)
        )

        val productsList = (productsListDataState as DataState.Success).data
        MatcherAssert.assertThat(productsList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            productsList!!.data.size,
            CoreMatchers.`is`(mockProducts.data.size)
        )

        coVerify(exactly = 1) { apiService.getProducts(imageSize = 750) }
        confirmVerified(apiService)
    }

}