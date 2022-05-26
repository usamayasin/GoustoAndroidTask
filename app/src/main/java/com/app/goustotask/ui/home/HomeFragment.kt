package com.app.goustotask.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import com.app.goustotask.R
import com.app.goustotask.adapters.ProductsAdapter
import com.app.goustotask.base.BaseFragment
import com.app.goustotask.databinding.HomeFragmentBinding
import com.app.goustotask.utils.Constants
import com.app.goustotask.utils.flowWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> HomeFragmentBinding
        get() = HomeFragmentBinding::inflate

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var productsAdapter: ProductsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
        initObservations()
    }


    private fun initListener() {

        binding.lifecycleOwner = this
        binding.viewModel = viewModel


        binding.searchEt.doAfterTextChanged {
            viewModel.filterProductsList(it.toString(), viewModel.getProductsList())
        }

        productsAdapter = ProductsAdapter { product ->
            val bundle = bundleOf(Constants.BUNDLE_KEY to product)
            findNavController().navigate(
                R.id.toProductDetailFragment,
                bundle
            )
        }
        binding.rvProducts.adapter = productsAdapter
    }

    private fun initObservations() {

        lifecycleScope.launch {
            launch {
                viewModel.responseMessage.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect {
                        showSnackbar(it, binding.root)
                    }
            }

        }

        viewModel.productsLiveData.observe(viewLifecycleOwner) { response ->
            // Update the UI, in this case
            response?.let {
                if (response.isNotEmpty()) {
                    productsAdapter.differ.submitList(response)
                }
            }
        }

        viewModel.filteredProductsLiveData.observe(viewLifecycleOwner) { response ->
            // Update the UI, in this case
            response?.let {
                if (response.isNotEmpty()) {
                    productsAdapter.differ.submitList(response)
                }
            }
        }


    }

    private fun collectFlows() {
        lifecycleScope.launch {
            launch {
                viewModel.responseMessage.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                    .collect {
                        showSnackbar(it, binding.root)
                    }
            }
        }
    }

}