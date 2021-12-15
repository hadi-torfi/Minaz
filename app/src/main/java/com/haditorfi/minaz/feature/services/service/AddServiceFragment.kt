package com.haditorfi.minaz.feature.services.service

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
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ServiceAddFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class AddServiceFragment : BaseFragment<ServiceAddFragmentBinding>() {
    private val viewModel: ServiceViewModel by viewModel()
    private val args by navArgs<AddServiceFragmentArgs>()
    private var errorMessage = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        binding.apply {
            activeEditMode()
            addService = args.serviceData
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

    private fun ServiceAddFragmentBinding.save() {
        val name = edtName.text.toString()
        val price = edtPrice.text.toString().replace(",", "")

        if (validate(name, price)) {
            val customer = Service(args.serviceData.id, name, price)

            if (args.serviceData.activeEditMode)
                viewModel.updateService(customer)
            else
                viewModel.insertService(customer)

            root.toast(getString(R.string.success))
            findNavController().navigateUp()
        } else {
            root.toast(errorMessage)
        }

    }

    private fun validate(name: String, price: String): Boolean {
        return if (name.isEmpty())
            manageValidate(getString(R.string.name_service_require))
        else if (price.isEmpty())
            manageValidate(getString(R.string.price_require))
        else
            true
    }

    private fun manageValidate(errorMessage: String): Boolean {
        this.errorMessage = errorMessage
        binding.root.hideKeyboard()
        return false
    }

    private fun ServiceAddFragmentBinding.activeEditMode() {
        if (args.serviceData.activeEditMode) {
            include.toolbarTitleTv.text = getString(R.string.edit)
            include.toolbarBtn.text = getString(R.string.edit)
        }
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.service_new)
            include.toolbarBtn.text = getString(R.string.save)
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceAddFragmentBinding =
        ServiceAddFragmentBinding.inflate(inflater, container, false)
}