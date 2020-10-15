package com.ghalistan.genshinimpactgachasimulation.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.ghalistan.genshinimpactgachasimulation.databinding.ActivityMainBinding
import com.ghalistan.genshinimpactgachasimulation.viewModels.MainViewModel
import kotlinx.android.synthetic.main.navigation_header.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.actualAppBar)

        val host =
            supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = host.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.mainDrawerLayout)

        binding.actualAppBar.setupWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.user.observe(this, { data ->
            binding.navView.tv_header_nav.text = data[0].username
            binding.navView.tv_primogem_amount.text = data[0].primogem.toString()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(binding.navHostFragment.id)
        return navController.navigateUp(appBarConfiguration) || navController.navigateUp()
    }
}