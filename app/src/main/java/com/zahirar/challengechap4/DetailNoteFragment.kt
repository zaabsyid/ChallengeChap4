package com.zahirar.challengechap4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zahirar.challengechap4.databinding.FragmentDetailNoteBinding
import com.zahirar.challengechap4.room.DataNote

class DetailNoteFragment : Fragment() {

    lateinit var binding : FragmentDetailNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = arguments?.getString("title")
        val content = arguments?.getString("content")

        binding.detailNote = DataNote(0, title!!, content!!)

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }
}