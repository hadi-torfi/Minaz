package com.haditorfi.minaz.feature.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.BaseFragment
import com.haditorfi.minaz.common.IPopup
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.CustomerFragmentBinding
import org.koin.android.ext.android.inject

class CustomerFragment : BaseFragment<CustomerFragmentBinding>(), IPopup<Customer> {
    private val viewModel: CustomerViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(Customer())
            }
            viewModel.customersLiveData.observe(viewLifecycleOwner) {
                if (it.isEmpty()) addCustomer()

                val viewAdapter =
                    CustomerAdapter(requireContext(), it, IItemClickListener = { item, customer ->
                        popUp(requireContext(), item, customer)
                    })

                rvCustomerList.run {
                    setHasFixedSize(true)
                    adapter = viewAdapter
                }
            }
        }
    }

    override fun initToolbar() {
        binding.apply {
            include.apply {
                toolbarTitleTv.text = getString(R.string.customer_list)
                toolbarBtn.text = getString(R.string.customer_new)
                toolbarBackBtn.setOnClickListener {
                    findNavController().navigateUp()
                }
            }
        }
    }


    private fun addCustomer() {
        val c1 = Customer("سمانه محمدی", "09166424100", "شوش خیابان مدرس")
        val c2 = Customer("نازنین طرفی", "09352623050", "شوش خیابان ")
        val c3 = Customer("سارینا رحمتی", "09352623055", "شوش ")
        viewModel.insertCustomer(c1, c2, c3)
    }

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CustomerFragmentBinding = CustomerFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(mClass: Customer) {
        viewModel.deleteCustomer(mClass)
    }

    override fun goToAddOrEditFromIPopup(mClass: Customer, editModeTrue: Boolean) {
        mClass.activeEditMode = editModeTrue
        val action =
            CustomerFragmentDirections.actionCustomerToAddOrEditCustomer(
                mClass
            )
        findNavController().navigate(action)
    }
}