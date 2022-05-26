package com.app.goustotask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.goustotask.data.model.ProductsDomainModel
import com.app.goustotask.databinding.ProductItemLayoutBinding

class ProductsAdapter(val onProductClicked: (product: ProductsDomainModel) -> Unit) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ProductItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount() = differ.currentList.size

    inner class ProductsViewHolder(private val itemBinding: ProductItemLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(product: ProductsDomainModel) {
            itemBinding.apply {
                data = product
                cardProduct.setOnClickListener {
                    onProductClicked(product)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<ProductsDomainModel>() {

        override fun areItemsTheSame(
            oldItem: ProductsDomainModel,
            newItem: ProductsDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ProductsDomainModel,
            newItem: ProductsDomainModel
        ): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
}
