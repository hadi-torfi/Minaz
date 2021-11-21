package com.haditorfi.minaz.feature.service

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.hideKeyboard
import com.haditorfi.minaz.common.toast
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ServiceAddFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class AddServiceFragment : Fragment() {
    private lateinit var binding: ServiceAddFragmentBinding
    private val viewModel: ServiceViewModel by viewModel()
    private val args by navArgs<AddServiceFragmentArgs>()
    private var errorMessage = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ServiceAddFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.service_new)
            include.toolbarBtn.text = getString(R.string.save)
            include.backBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            activeEditMode()
            addService = args.serviceData
            include.toolbarBtn.setOnClickListener {
                save()
            }
        }
    }

    private fun ServiceAddFragmentBinding.save() {

        val name = edtName.text.toString()
        val price = edtPrice.text.toString()
        val count = edtCount.text.toString()

        if (validate(name, price, count)) {
            val customer = Service(args.serviceData.id, name, price, count)

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

    private fun validate(name: String, price: String, count: String): Boolean {
        return if (name.isEmpty())
            manageValidate(getString(R.string.name_require))
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

    private fun ServiceAddFragmentBinding.activeEditMode() {
        if (args.serviceData.activeEditMode) {
            include.toolbarTitleTv.text = getString(R.string.edit)
            include.toolbarBtn.text = getString(R.string.edit)
        }
    }
}