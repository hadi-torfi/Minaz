package com.haditorfi.minaz.feature.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.FakeData
import com.haditorfi.minaz.common.gone
import com.haditorfi.minaz.common.visible
import com.haditorfi.minaz.feature.customer.CustomerViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private val customerViewModel: CustomerViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        customerViewModel.customersLiveData.observe(this) {
            if (it.isEmpty()) {
                val fakeData: FakeData by inject()
                fakeData.createFakeData()
            }
        }

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNavigationView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navDetailProvideService -> bottomNavigationView.gone()
                else -> bottomNavigationView.visible()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}