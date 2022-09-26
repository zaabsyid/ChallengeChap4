package com.zahirar.challengechap4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.zahirar.challengechap4.databinding.FragmentEditNoteBinding
import com.zahirar.challengechap4.room.DataNote
import com.zahirar.challengechap4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class EditNoteFragment : Fragment() {

    lateinit var binding : FragmentEditNoteBinding
    lateinit var noteVM : NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        noteVM = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        val title = arguments?.getString("title")
        val content = arguments?.getString("content")

        binding.editNote = DataNote(0, title!!, content!!)

        binding.btnEditNote.setOnClickListener {
            val addedNote = DataNote(
                0,
                binding.edtTitle.text.toString(),
                binding.edtContent.text.toString()
            )
            noteVM.updateNotes(addedNote)
            requireActivity().onBackPressed()
        }
    }
}