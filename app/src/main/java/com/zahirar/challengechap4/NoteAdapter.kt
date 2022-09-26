package com.zahirar.challengechap4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zahirar.challengechap4.databinding.ItemNoteBinding
import com.zahirar.challengechap4.room.DataNote
import java.util.ArrayList

class NoteAdapter(var listNote : List<DataNote>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    var onDeleteclick: ((DataNote) -> Unit)? = null

    class ViewHolder(var binding : ItemNoteBinding):RecyclerView.ViewHolder(binding.root) {

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        holder.binding.tvNoteId.text = listNote[position].id.toString()
        holder.binding.tvNoteTitle.text = listNote[position].title
        val title = listNote[position].title
        val content = listNote[position].content
        holder.binding.cvNote.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putString("content", content)

                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_detailNoteFragment, bundle)
            }
        })
        holder.binding.btnEditNote.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val bundle = Bundle()
                bundle.putString("title", title)
                bundle.putString("content", content)

                Navigation.findNavController(holder.itemView).navigate(R.id.action_homeFragment_to_editNoteFragment, bundle)
            }
        })
        holder.binding.btnDeleteNote.setOnClickListener {
            onDeleteclick?.invoke(listNote[position])
        }
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setNoteData(listNote: ArrayList<DataNote>)
    {
        this.listNote = listNote
    }
}