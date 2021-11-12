package com.haditorfi.minaz.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DashboardFragment : MyFragment() {
    private val viewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.customersLiveData.observe(viewLifecycleOwner) {
            Timber.i("customers: $it")
        }
    }
}