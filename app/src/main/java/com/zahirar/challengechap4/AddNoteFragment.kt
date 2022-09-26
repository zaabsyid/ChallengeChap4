package com.zahirar.challengechap4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.zahirar.challengechap4.databinding.FragmentAddNoteBinding
import com.zahirar.challengechap4.room.DataNote
import com.zahirar.challengechap4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class AddNoteFragment : Fragment() {

    lateinit var binding : FragmentAddNoteBinding
    lateinit var noteVM :NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        noteVM = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)

        binding.btnAddNote.setOnClickListener {
            val addedNote = DataNote(
                0,
                binding.edtNoteTitle.text.toString(),
                binding.edtNoteContent.text.toString()
            )
            noteVM.insertNotes(addedNote)
            requireActivity().onBackPressed()
        }
    }
}