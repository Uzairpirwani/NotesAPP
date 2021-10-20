package com.example.notes.ui.fragment

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.model.Notes
import com.example.notes.R
import com.example.notes.viewmodel.NotesViewModel
import com.example.notes.databinding.FragmentEditNoteBinding
import com.example.notes.model.NotesColor
import com.google.android.material.bottomsheet.BottomSheetDialog
import hideKeyboard


class EditNoteFragment : Fragment() {

    private val oldNotes by navArgs<EditNoteFragmentArgs>()
    private lateinit var binding: FragmentEditNoteBinding
    private val viewModel: NotesViewModel by viewModels()

    private var backgroundColor = NotesColor.ORANGE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = FragmentEditNoteBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)

        binding.editTitle.setText(oldNotes.data.title)
        binding.editSubTitle.setText(oldNotes.data.subTitle)


        binding.btnEditSaveNotes.setOnClickListener {
            updateNotes(it)
            hideKeyboard()
        }

        setNoteColor(oldNotes.data.noteColor)

        binding.cbtn1.setOnClickListener {
            setNoteColor(NotesColor.ORANGE)
        }

        binding.cbtn2.setOnClickListener {
            setNoteColor(NotesColor.YELLOW)
        }
        binding.cbtn3.setOnClickListener {
            setNoteColor(NotesColor.PINK)
        }
        binding.cbtn4.setOnClickListener {
            setNoteColor(NotesColor.TEAL)
        }
        binding.cbtn5.setOnClickListener {
            setNoteColor(NotesColor.PURPLE)
        }

        return binding.root
    }

    private fun updateNotes(it: View?) {

        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()

        val data = Notes(
            oldNotes.data.id,
            title,
            subTitle,
            backgroundColor
        )
        viewModel.updateNotes(data)
        Toast.makeText(requireContext(), "Notes updated Successfully", Toast.LENGTH_SHORT)
            .show()

        Navigation.findNavController(it!!)
            .navigate(R.id.action_editNoteFragment_to_homeFragment)

    }

    private fun setNoteColor(color: NotesColor) {
        backgroundColor = color
        binding.editNoteFragment.setBackgroundResource(backgroundColor.color)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {

            val bottomSheet = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textViewYes = bottomSheet.findViewById<TextView>(R.id.dialog_Yes)
            val textViewNo = bottomSheet.findViewById<TextView>(R.id.dialog_No)

            textViewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }
            textViewYes?.setOnClickListener {
                viewModel.deleteNotes(oldNotes.data.id!!)
                bottomSheet.dismiss()
                findNavController().navigateUp()

            }

            bottomSheet.show()

        }
        return super.onOptionsItemSelected(item)
    }

}