package com.haditorfi.minaz.feature.customer

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
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.CustomerFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class CustomerFragment : Fragment() {
    private lateinit var binding: CustomerFragmentBinding
    private val viewModel: CustomerViewModel by viewModel()
    private val customerInject: Customer by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomerFragmentBinding.inflate(inflater, container, false)
        initToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.toolbarBtn.setOnClickListener {
            goToAddOrEditFragment()
        }
        viewModel.customersLiveData.observe(viewLifecycleOwner) {
            if (it.isEmpty()) addCustomer()

            val viewAdapter =
                CustomerAdapter(requireContext(), it, IItemClickListener = { item, customer ->
                    popUp(item, customer)
                })

            binding.rvCustomerList.run {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    private fun popUp(view: View, customer: Customer) {
        val popup = PopupMenu(context, view)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.options, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.edit_menu -> {
                    goToAddOrEditFragment(customer, true)
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
                            viewModel.deleteCustomer(customer)
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
        customer: Customer = Customer(),
        editModeTrue: Boolean = false
    ) {
        customer.activeEditMode = editModeTrue
        val action =
            CustomerFragmentDirections.actionCustomerToAddOrEditCustomer(
                customer
            )
        findNavController().navigate(action)
    }

    private fun initToolbar() {
        binding.apply {
            include.toolbarTitleTv.text = getString(R.string.customer_list)
            include.toolbarBtn.text = getString(R.string.customer_new)
            include.toolbarBackBtn.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun addCustomer() {
        val c1 = Customer("سمانه محمدی", "09166424100", "شوش خیابان مدرس")
        val c2 = Customer("نازنین طرفی", "09352623050", "شوش خیابان ")
        val c3 = Customer("سارینا رحمتی", "09352623055", "شوش ")
        viewModel.insertCustomer(c1, c2, c3)
    }
}