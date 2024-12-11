package com.ubaya.lulusgaming.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.ActivityMainBinding

//ALL DONE
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val PREFS_NAME = "UserSession"
    private val KEY_LOGGED_IN = "isLoggedIn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.itemWhat, R.id.itemWho, R.id.itemSchedule), binding.drawerLayout
        )
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
        NavigationUI.setupWithNavController(binding.navView, navController)

        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean(KEY_LOGGED_IN, false)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.signInFragment || destination.id == R.id.signUpFragment) {
                binding.bottomNav.visibility = View.GONE
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                binding.bottomNav.visibility = View.VISIBLE
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemLogOut -> {
                    logOut()
                    true
                }
                else -> {
                    NavigationUI.onNavDestinationSelected(menuItem, navController)
                    binding.drawerLayout.closeDrawers()
                    true
                }
            }
        }

        // Jika pengguna sudah login sebelumnya, langsung navigasi ke halaman utama
        if (isLoggedIn) {
            navController.navigate(R.id.itemWhat)
        }
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        navController = (supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment).navController
//
//        appBarConfiguration = AppBarConfiguration(
//            setOf(R.id.itemWhat, R.id.itemWho, R.id.itemSchedule), binding.drawerLayout
//        )
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
//
////        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)
////        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as NavHostFragment
////        navController = navHostFragment.navController
////
//        binding.bottomNav.setupWithNavController(navController)
//        NavigationUI.setupWithNavController(binding.navView, navController)
////        NavigationUI.setupWithNavController(navController)
//
//        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        val isLoggedIn = sharedPreferences.getBoolean(KEY_LOGGED_IN, false)
//
//        if (!isLoggedIn) {
//            navController.navigate(R.id.signInFragment)
//        } else {
//            navController.navigate(R.id.itemWhat)
//        }
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.signInFragment || destination.id == R.id.signUpFragment) {
//                binding.bottomNav.visibility = View.GONE
//            } else {
//                binding.bottomNav.visibility = View.VISIBLE
//            }
//        }
//
//        binding.navView.setNavigationItemSelectedListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.itemLogOut -> {
//                    logOut()
//                    true
//                }
//                else -> {
//                    // Menangani item-menu lainnya
//                    NavigationUI.onNavDestinationSelected(menuItem, navController)
//                    binding.drawerLayout.closeDrawers()
//                    true
//                }
//            }
//        }
//
//        if (isLoggedIn) {
//            navController.navigate(R.id.itemWhat)
//        }
//
//    }
//
//    private fun logOut() {
//        // Hapus status login dari SharedPreferences
//        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
//        with(sharedPreferences.edit()) {
//            putBoolean(KEY_LOGGED_IN, false)
//            apply()
//        }
//
//        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
//        navController.navigate(R.id.signInFragment)
//    }

    private fun logOut() {
        val sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Menghapus semua data preferensi (termasuk status login)
        editor.apply()

        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

        // Menutup drawer
        binding.drawerLayout.closeDrawers()

        // Navigasi ke SignInFragment
        navController.popBackStack(navController.graph.startDestinationId, true)
        navController.navigate(R.id.signInFragment)
    }

    override fun onSupportNavigateUp(): Boolean {
        val currentDestination = navController.currentDestination?.id
        if (currentDestination == R.id.signInFragment || currentDestination == R.id.signUpFragment) {
            return false // Tidak lakukan apa-apa
        }
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }



//
//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
////        return NavigationUI.navigateUp(navController, binding.drawerLayout) || super.onSupportNavigateUp()
//    }
}