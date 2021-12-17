package com.haditorfi.minaz.feature.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.databinding.ProductFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : BaseFragment<ProductFragmentBinding>(), IPopup<Product> {
    private val viewModel: ProductViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allProduct.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createProduct()
                val serviceViewAdapter =
                    ProductAdapter(requireContext(), it, IItemClickListener = { item, service ->
                        popUp(requireContext(), item, service)
                    })

                rvProduct.run {
                    setHasFixedSize(true)
                    adapter = serviceViewAdapter
                }
            }
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(Product())
            }
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.product)
                toolbarBtn.text = getString(R.string.product_new)
                toolbarBackBtn.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    private fun createProduct() {
        val p1 = Product("کرم ووکس", "180000", "5")
        val p2 = Product("کرم ستاره", "30000", "6")
        viewModel.insertProduct(p1, p2)
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProductFragmentBinding = ProductFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(myClass: Product) {
        viewModel.deleteProduct(myClass)
    }

    override fun goToAddOrEditFromIPopup(myClass: Product, editModeTrue: Boolean) {
        myClass.activeEditMode = editModeTrue
        val action =
            ProductFragmentDirections.actionProductToAddProduct(
                myClass
            )
        findNavController().navigate(action)
    }
}