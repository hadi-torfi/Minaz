package com.haditorfi.minaz.feature.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.databinding.ProductFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ProductFragment : Fragment() {
    lateinit var binding: ProductFragmentBinding
    private val viewModel: ProductViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel.allProduct.observe(viewLifecycleOwner) {
                if (it.isEmpty()) createProduct()
                val serviceViewAdapter =
                    ProductAdapter(requireContext(), it, IItemClickListener = { item, service ->
                        popUp(item, service)
                    })
                rvProduct.adapter = serviceViewAdapter
            }
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFragment()
            }
        }
    }


    private fun popUp(view: View, product: Product) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(product, true)
                    return@setOnMenuItemClickListener true
                }
                R.id.delete_menu -> {
                    MaterialAlertDialogBuilder(view.context)
                        .setTitle(resources.getString(R.string.delete_message))
                        .setIcon(R.drawable.ic_error)
                        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                            viewModel.deleteProduct(product)
                        }
                        .show()
                    return@setOnMenuItemClickListener true
                }
                else ->
                    return@setOnMenuItemClickListener false
            }
        }
        popup.show()
    }

    private fun goToAddOrEditFragment(
        product: Product = Product(),
        editModeTrue: Boolean = false
    ) {
        product.activeEditMode = editModeTrue
        val action =
            ProductFragmentDirections.actionProductToAddProduct(
                product
            )
        findNavController().navigate(action)
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.product)
            include.toolbarBtn.text = getString(R.string.product_new)
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun createProduct() {
        val p1 = Product("کرم ووکس", "180000", "5")
        val p2 = Product("کرم ستاره", "30000", "6")
        viewModel.insertProduct(p1, p2)
    }
}