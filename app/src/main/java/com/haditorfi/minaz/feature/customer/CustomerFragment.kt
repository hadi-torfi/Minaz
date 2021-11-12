package com.haditorfi.minaz.feature.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.DATA_KEY
import com.haditorfi.minaz.data.customer.Customer
import org.koin.android.viewmodel.ext.android.viewModel

class CustomerFragment : Fragment() {
    private val viewModel: CustomerViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.customer_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.customersLiveData.observe(viewLifecycleOwner) {
            val viewAdapter = CustomerAdapter(it)

            view.findViewById<RecyclerView>(R.id.rv_customer_list).run {
                setHasFixedSize(true)
                adapter = viewAdapter
            }
        }
    }

    class CustomerAdapter(private val myDataset: List<Customer>) :
        RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

        class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            // create a new view
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.customer_view_item, parent, false)


            return ViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.item.findViewById<TextView>(R.id.txt_customer_name).text =
                myDataset[position].name
            holder.item.findViewById<TextView>(R.id.txt_customer_mobile).text =
                myDataset[position].mobile

            holder.item.setOnClickListener {
                val bundle =
                    bundleOf(
                        DATA_KEY to myDataset[position]
                    )

                holder.item.findNavController().navigate(
                    R.id.action_customer_to_editCustomer,
                    bundle
                )
            }
        }

        override fun getItemCount() = myDataset.size

    }

}