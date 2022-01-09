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
import org.koin.androidx.viewmodel.ext.android.viewModel

class CustomerFragment : BaseFragment<CustomerFragmentBinding>(), IPopup<Customer> {
    private val viewModel: CustomerViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            include.toolbarBtn.setOnClickListener {
                goToAddOrEditFromIPopup(Customer())
            }
            viewModel.customersLiveData.observe(viewLifecycleOwner) {
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

    override fun createViewBinging(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): CustomerFragmentBinding = CustomerFragmentBinding.inflate(inflater, container, false)

    override fun deleteFromIPopup(myClass: Customer) {
        viewModel.deleteCustomer(myClass)
    }

    override fun goToAddOrEditFromIPopup(myClass: Customer, editModeTrue: Boolean) {
        myClass.activeEditMode = editModeTrue
        val action =
            CustomerFragmentDirections.actionCustomerToAddOrEditCustomer(
                myClass
            )
        findNavController().navigate(action)
    }
}