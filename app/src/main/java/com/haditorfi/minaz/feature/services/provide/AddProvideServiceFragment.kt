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
import com.haditorfi.minaz.feature.staff.StaffViewModel
import org.koin.android.ext.android.inject
import timber.log.Timber
import kotlin.collections.set


class AddProvideServiceFragment : BaseFragment<ServiceProvideAddFragmentBinding>() {
    private val customerViewModel: CustomerViewModel by inject()
    private val staffViewModel: StaffViewModel by inject()
    private val customerArray: HashMap<Long, String> = hashMapOf()
    private val staffArray: HashMap<Int, String> = hashMapOf()
    private var customerName: String = ""
    private var staffName: String = ""
    private var customerId: Long = 0
    private var staffId: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            staffViewModel.staffsLiveData.observe(viewLifecycleOwner) { staffs ->
                for (staff in staffs) {
                    staffArray[staff.id] = staff.name
                }
                val adapter: ArrayAdapter<String> =
                    ArrayAdapter<String>(
                        requireContext(),
                        android.R.layout.select_dialog_item,
                        ArrayList<String>(staffArray.values)
                    )
                staffAutoCompleteView.apply {
                    threshold = 1
                    setAdapter(adapter)
                }
            }
            staffAutoCompleteView.setOnItemClickListener { parent, _, position, _ ->
                staffId = getStaffId(parent, position)
            }
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

    private fun getCustomerId(parent: AdapterView<*>, position: Int): Long {
        customerName = parent.getItemAtPosition(position).toString()
        val filter = customerArray.filterValues {
            it == customerName
        }
        return filter.keys.first()
    }

    private fun getStaffId(parent: AdapterView<*>, position: Int): Int {
        staffName = parent.getItemAtPosition(position).toString()
        val filter = staffArray.filterValues {
            it == staffName
        }
        return filter.keys.first()
    }

    private fun ServiceProvideAddFragmentBinding.save() {
        if (staffId <= 0 || staffAutoCompleteView.text.toString() != staffName) {
            root.toast(getString(R.string.staff_select))
        } else if (customerId <= 0 || customerAutoCompleteView.text.toString() != customerName) {
            root.toast(getString(R.string.customer_select))
        } else {
            Timber.i("customerId save: $customerId")
            Timber.i("staffId save: $staffId")
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