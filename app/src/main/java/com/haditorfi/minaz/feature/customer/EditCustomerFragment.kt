package com.haditorfi.minaz.feature.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.DATA_KEY
import com.haditorfi.minaz.data.customer.Customer

class EditCustomerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val customer = arguments?.getParcelable<Customer>(DATA_KEY)

        view.findViewById<TextView>(R.id.txt_user_name).text = customer?.name
        view.findViewById<TextView>(R.id.txt_user_mobile).text = customer?.mobile
    }
}