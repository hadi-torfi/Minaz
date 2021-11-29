package com.haditorfi.minaz.feature.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.hideKeyboard
import com.haditorfi.minaz.common.isPhone
import com.haditorfi.minaz.common.toast
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.CustomerAddFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AddCustomerFragment : Fragment() {
    private lateinit var binding: CustomerAddFragmentBinding
    private val args by navArgs<AddCustomerFragmentArgs>()
    private val viewModel: CustomerViewModel by viewModel()
    private var errorMessage = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomerAddFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            activeEditMode()
            edtCustomer = args.customData

            include.toolbarBtn.setOnClickListener {
                save()
            }
        }
    }

    private fun CustomerAddFragmentBinding.save() {
        val name = edtCustomerName.text.toString()
        val mobile = edtCustomerMobile.text.toString()
        val address = edtCustomerAddress.text.toString()
        if (validate(name, mobile)) {
            val customer = Customer(args.customData.id, name, mobile, address)

            if (args.customData.activeEditMode)
                viewModel.updateCustomer(customer)
            else
                viewModel.insertCustomer(customer)

            root.toast(getString(R.string.success))
            findNavController().navigateUp()
        } else {
            root.toast(errorMessage)
        }
    }

    private fun validate(name: String, mobile: String): Boolean {
        return if (name.isEmpty())
            manageValidate(getString(R.string.name_require))
        else if (mobile.isEmpty())
            manageValidate(getString(R.string.mobil_require))
        else if (!mobile.isPhone())
            manageValidate(getString(R.string.isPhone_message))
        else
            true
    }

    private fun manageValidate(errorMessage: String): Boolean {
        this.errorMessage = errorMessage
        binding.root.hideKeyboard()
        return false
    }

    private fun CustomerAddFragmentBinding.activeEditMode() {
        if (args.customData.activeEditMode) {
            include.toolbarTitleTv.text = getString(R.string.edit)
            include.toolbarBtn.text = getString(R.string.edit)
        }
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.customer_new)
            include.toolbarBtn.text = getString(R.string.save)
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}