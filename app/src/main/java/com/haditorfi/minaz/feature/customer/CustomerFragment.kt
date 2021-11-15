package com.haditorfi.minaz.feature.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.haditorfi.minaz.databinding.CustomerFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CustomerFragment : Fragment() {
    private lateinit var binding: CustomerFragmentBinding
    private val viewModel: CustomerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CustomerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.customersLiveData.observe(viewLifecycleOwner) {
            val viewAdapter = MyAdapter(requireContext(), it)
            binding.rvCustomerList.run {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }
}