package com.haditorfi.minaz.feature.services.provide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.toast
import com.haditorfi.minaz.databinding.ServiceProvideAddFragmentBinding
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber
import kotlin.collections.set


class AddProvideServiceFragment : BaseFragment<ServiceProvideAddFragmentBinding>() {
    private val customerViewModel: CustomerViewModel by inject()
    private val customerArray: HashMap<Long, String> = hashMapOf()
    private var customerId: Set<Long> = setOf()
    private var customerName: Any = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            customerViewModel.customersLiveData.observe(viewLifecycleOwner) { customers ->
                for (customer in customers) {
                    customerArray[customer.id] = customer.name
                }
                val adapter: ArrayAdapter<String> =
                    ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.select_dialog_item,
                        ArrayList<String>(customerArray.values)
                    )
                customerAutoCompleteView.apply {
                    threshold = 1
                    setAdapter(adapter)
                }
            }
            customerAutoCompleteView.setOnItemClickListener { parent, _, position, _ ->
                customerId = getCustomerId(parent, position)
            }
            include.toolbarBtn.setOnClickListener {
                save()
            }
        }
    }

    private fun getCustomerId(parent: AdapterView<*>, position: Int): Set<Long> {
        customerName = parent.getItemAtPosition(position)
        val filter = customerArray.filterValues {
            it == customerName
        }
        Timber.i("customerId: ${filter.keys}")
        return filter.keys
    }

    private fun save() {
        if (customerId.isEmpty() || !binding.customerAutoCompleteView.text.equals(customerName)) {
            binding.root.toast(getString(R.string.customer_select))
        } else {
            val ci = customerId
            Timber.i("customerId save: $ci")
        }

    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ServiceProvideAddFragmentBinding =
        ServiceProvideAddFragmentBinding.inflate(inflater, container, false)

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(com.haditorfi.minaz.R.string.service_new)
                toolbarBtn.text = getString(com.haditorfi.minaz.R.string.save)
                toolbarBackBtn.setOnClickListener { findNavController().popBackStack() }
            }
        }
    }
}