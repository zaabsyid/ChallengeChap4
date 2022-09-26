package com.zahirar.challengechap4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.zahirar.challengechap4.databinding.FragmentRegisterBinding
import com.zahirar.challengechap4.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    lateinit var binding : FragmentSplashBinding
    lateinit var sharedPref : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        var getDataUsername = sharedPref.getString("username", "")
        var getDataPassword = sharedPref.getString("password", "")

        Handler().postDelayed({
            if (getDataUsername == "" && getDataPassword == ""){
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment)
            } else{
                Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_homeFragment)
            }
        }, 3000)
    }
}