package com.zahirar.challengechap4

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.zahirar.challengechap4.databinding.FragmentHomeBinding
import com.zahirar.challengechap4.room.DataNote
import com.zahirar.challengechap4.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    lateinit var binding :FragmentHomeBinding
    lateinit var sharedPref : SharedPreferences
    lateinit var adapterNote: NoteAdapter
    lateinit var notesVM : NoteViewModel
    var noteDB : NoteDatabase? =  null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireActivity().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        noteDB = NoteDatabase.getInstance(requireContext())

        setRecyclerView()
        notesVM = ViewModelProvider(requireActivity()).get(NoteViewModel::class.java)
        notesVM.getAllNotesObserver().observe(viewLifecycleOwner) {
            adapterNote.setNoteData(it as ArrayList<DataNote>)
            adapterNote.notifyDataSetChanged()
        }

        var getDataFullname = sharedPref.getString("fullname", "")
        binding.tvFullname.text = "Welcome, " + getDataFullname

        binding.tvLogout.setOnClickListener {
            var pref = sharedPref.edit()
            pref.clear()
            pref.apply()
            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment)
        }

        binding.btnAddNote.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addNoteFragment)
        }

        adapterNote.onDeleteclick = {
            Toast.makeText(context, "Catatan Berhasil dihapus", Toast.LENGTH_SHORT).show()
            notesVM.deleteNotes(it)
        }
    }

    fun setRecyclerView() {
        adapterNote = NoteAdapter(ArrayList())
        binding.rvListNote.adapter = adapterNote
        binding.rvListNote.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}