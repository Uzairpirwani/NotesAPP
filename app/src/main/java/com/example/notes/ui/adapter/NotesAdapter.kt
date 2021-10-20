package com.example.notes.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.model.Notes
import com.example.notes.databinding.ItemNotesBinding
import com.example.notes.ui.fragment.HomeFragmentDirections
//import com.example.utils.FoldCornerCard

import android.graphics.drawable.ShapeDrawable
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.shape.TriangleEdgeTreatment


class NotesAdapter(
    private val context: Context,
    private val noteList: List<Notes>,
    private val deleteNoteListener: (noteId: Int?) -> Unit
) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    class NotesViewHolder(val binding: ItemNotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context)))
    }


    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val data = noteList[position]
        with(holder.binding) {


            val backgroundShapeModel = ShapeAppearanceModel.builder()
                .setTopLeftCorner(CornerFamily.ROUNDED, 16F)
                .setBottomLeftCorner(CornerFamily.ROUNDED, 16F)
                .setBottomRightCorner(CornerFamily.ROUNDED, 16F)
                .setTopRightCorner(CornerFamily.CUT, 35F)
                .build()

            cardViewNotes.background = MaterialShapeDrawable(backgroundShapeModel).apply {
                fillColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        data.noteColor.color
                    )
                )
            }
            val bottomSheet = BottomSheetDialog(context, R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_Yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_No)

            notesTitle.text = data.title
            noteSubtitle.text = data.subTitle
            deleteIc.setOnClickListener {
                bottomSheet.show()
                textViewNo?.setOnClickListener {
                    bottomSheet.dismiss()
                }
                textViewYes?.setOnClickListener {
                    deleteNoteListener(data.id)
                    bottomSheet.dismiss()

                }
            }
            root.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(data)
                Navigation.findNavController(it).navigate(action)
            }
        }

    }


    override fun getItemCount() = noteList.size

}