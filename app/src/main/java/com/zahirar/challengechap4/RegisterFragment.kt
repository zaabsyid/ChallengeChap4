package com.zahirar.challengechap4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.zahirar.challengechap4.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    lateinit var binding : FragmentRegisterBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            var getUsername = binding.edtUsername.text.toString()
            var getFullName = binding.edtFullname.text.toString()
            var getPassword = binding.edtPassword.text.toString()

            var addData = sharedPref.edit()
            addData.putString("username", getUsername)
            addData.putString("fullname", getFullName)
            addData.putString("password", getPassword)
            addData.apply()
            Toast.makeText(context, "Register Berhasil", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.tvLogin.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}