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
import com.zahirar.challengechap4.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {
            var getDataUsername = sharedPref.getString("username", "")
            var getDataPassword = sharedPref.getString("password", "")
            if (getDataUsername == "" && getDataPassword == ""){
                Toast.makeText(context, "Data tidak ditemukan, Register Dahulu", Toast.LENGTH_SHORT).show()
            } else{
                val username = binding.edtUsername.text.toString()
                var addUser = sharedPref.edit()
                addUser.putString("username", username)
                addUser.apply()
                Toast.makeText(context, "Login Berhasil", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
            }
        }

        binding.tvRegister.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}