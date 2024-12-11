package com.ubaya.lulusgaming.view

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
import com.ubaya.lulusgaming.databinding.FragmentSignUpBinding
import com.ubaya.lulusgaming.model.Account
import com.ubaya.lulusgaming.viewmodel.SignUpViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewmodel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottomNav)?.visibility = View.GONE

        viewmodel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding.btnSubmit.isEnabled = false

        binding.checkBoxAgreement.setOnCheckedChangeListener { _, isChecked ->
            binding.btnSubmit.isEnabled = isChecked
        }

        binding.btnBack.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.actionSignIn)
        }

        binding.btnSubmit.setOnClickListener {
            val firstName = binding.inputFirstName.text.toString()
            val lastName = binding.inputLastName.text.toString()
            val username = binding.inputUsername.text.toString()
            val password = binding.inputPassword.text.toString()
            val repeatPassword = binding.inputRepeatPassword.text.toString()
            val isChecked = binding.checkBoxAgreement.isChecked

            if (firstName.isBlank()) {
                Toast.makeText(view.context, "Nama Depan tidak boleh kosong", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (lastName.isBlank()) {
                Toast.makeText(view.context, "Nama Belakang tidak boleh kosong", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (username.isBlank()) {
                Toast.makeText(view.context, "Username tidak boleh kosong", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (password.isBlank()) {
                Toast.makeText(view.context, "Password tidak boleh kosong", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (repeatPassword.isBlank()) {
                Toast.makeText(view.context, "Repeat Password tidak boleh kosong", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }



            if (password != repeatPassword) {
                Toast.makeText(view.context, "Password dan Repeat Password tidak sama", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (!isChecked) {
                Toast.makeText(view.context, "Anda harus menyetujui persyaratan", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val account = Account(firstName, lastName, username, password)

            val list = listOf(account)
            viewmodel.addAccount(list)

            Toast.makeText(view.context, "Akun berhasil ditambahkan", Toast.LENGTH_LONG).show()

            Navigation.findNavController(view).popBackStack()
        }
    }

}