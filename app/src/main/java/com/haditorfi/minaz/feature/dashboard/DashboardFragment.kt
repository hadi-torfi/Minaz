package com.haditorfi.minaz.feature.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MyFragment
import kotlinx.android.synthetic.main.dashboard_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class DashboardFragment : MyFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_customer_list.setOnClickListener {
            findNavController().navigate(R.id.action_dashboard_to_customer)
        }
    }
}