package com.ubaya.lulusgaming.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ubaya.lulusgaming.R
import com.ubaya.lulusgaming.databinding.FragmentSignInBinding
import com.ubaya.lulusgaming.databinding.FragmentSignUpBinding
import com.ubaya.lulusgaming.viewmodel.SignUpViewModel

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var viewmodel : SignUpViewModel

    private val PREFS_NAME = "UserSession"
    private val KEY_LOGGED_IN = "isLoggedIn"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.GONE

        checkLoginStatus()

        viewmodel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        observeViewModel()

        binding.btnSignUp.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.actionSignUp)
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.inputLoginUsername.text.toString()
            val password = binding.inputLoginPassword.text.toString()

            if(username.isBlank() || password.isBlank()) {
                Toast.makeText(view.context, "username dan password harus diisi", Toast.LENGTH_LONG).show()
            } else {
                viewmodel.verifyAccount(username, password)
            }
        }

    }

    private fun observeViewModel() {
        viewmodel.accountLD.observe(viewLifecycleOwner) { account ->
            if (account != null) {

                saveLoginStatus(true)

                Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(binding.root).navigate(R.id.actionItemWhat)
            } else {
                Toast.makeText(requireContext(), "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun saveLoginStatus(isLoggedIn: Boolean) {
        val sharedPreferences = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        editor?.putBoolean(KEY_LOGGED_IN, isLoggedIn)
        editor?.apply()
    }

    private fun checkLoginStatus() {
        val sharedPreferences = activity?.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences?.getBoolean(KEY_LOGGED_IN, false) ?: false

        if (isLoggedIn) {
            Navigation.findNavController(binding.root).navigate(R.id.actionItemWhat)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.VISIBLE
    }
    override fun onResume() {
        super.onResume()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.GONE
    }
    override fun onPause() {
        super.onPause()
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.VISIBLE
    }
}