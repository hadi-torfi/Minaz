package com.haditorfi.minaz.feature.product

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.hideKeyboard
import com.haditorfi.minaz.common.toast
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.databinding.ProductAddFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AddProductFragment : BaseFragment<ProductAddFragmentBinding>() {
    private val viewModel: ProductViewModel by viewModel()
    private val args by navArgs<AddProductFragmentArgs>()
    private var errorMessage = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activeEditMode()
            addProduct = args.productData
            include.toolbarBtn.setOnClickListener {
                save()
            }
            edtPrice.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    edtPrice.removeTextChangedListener(this)
                    try {
                        var originalString: String = s.toString()
                        if (originalString.contains(",")) {
                            originalString = originalString.replace(",".toRegex(), "")
                        }
                        val longVal: Long = originalString.toLong()
                        val formatter: DecimalFormat =
                            NumberFormat.getInstance(Locale.US) as DecimalFormat
                        formatter.applyPattern("#,###,###,###")
                        val formattedString: String = formatter.format(longVal)

                        //setting text after format to EditText
                        edtPrice.setText(formattedString)
                        edtPrice.setSelection(edtPrice.length())
                    } catch (nfe: NumberFormatException) {
                        nfe.printStackTrace()
                    }

                    edtPrice.addTextChangedListener(this)
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    private fun ProductAddFragmentBinding.save() {
        val name = edtName.text.toString()
        val price = edtPrice.text.toString().replace(",", "")
        val count = edtCount.text.toString()

        if (validate(name, price, count)) {
            val customer = Product(args.productData.id, name, price, count)

            if (args.productData.activeEditMode)
                viewModel.updateProduct(customer)
            else
                viewModel.insertProduct(customer)

            root.toast(getString(R.string.success))
            findNavController().navigateUp()
        } else {
            root.toast(errorMessage)
        }

    }

    private fun validate(name: String, price: String, count: String): Boolean {
        return if (name.isEmpty())
            manageValidate(getString(R.string.name_product_require))
        else if (price.isEmpty())
            manageValidate(getString(R.string.price_require))
        else if (count.isEmpty())
            manageValidate(getString(R.string.count_require))
        else
            true
    }

    private fun manageValidate(errorMessage: String): Boolean {
        this.errorMessage = errorMessage
        binding.root.hideKeyboard()
        return false
    }

    private fun ProductAddFragmentBinding.activeEditMode() {
        if (args.productData.activeEditMode) {
            include.toolbarTitleTv.text = getString(R.string.edit)
            include.toolbarBtn.text = getString(R.string.edit)
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.product_new)
                toolbarBtn.text = getString(R.string.save)
                toolbarBackBtn.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ProductAddFragmentBinding = ProductAddFragmentBinding.inflate(inflater, container, false)
}