package com.app.goustotask.ui.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.app.goustotask.base.BaseFragment
import com.app.goustotask.data.model.ProductsDomainModel
import com.app.goustotask.databinding.ProductDetailFragmentBinding
import com.app.goustotask.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<ProductDetailFragmentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ProductDetailFragmentBinding
        get() = ProductDetailFragmentBinding::inflate

    private var product: ProductsDomainModel? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        this.product = requireArguments().getParcelable(Constants.BUNDLE_KEY)
        this.product?.let {
            bind(it)
        }
    }

    private fun bind(product: ProductsDomainModel) {
        arguments?.let {
            binding.apply {
                data = product
            }
        }
    }
}