package com.example.notes.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notes.model.Notes
import com.example.notes.R
import com.example.notes.viewmodel.NotesViewModel
import com.example.notes.databinding.FragmentCreateNoteBinding
import com.example.notes.model.NotesColor
import com.example.utils.hideKeyboard

class CreateNoteFragment : Fragment() {
    private lateinit var binding: FragmentCreateNoteBinding
    private val viewModel: NotesViewModel by viewModels()

    private var backgroundColor = NotesColor.ORANGE
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreateNoteBinding.inflate(layoutInflater, container, false)

        setNoteColor(NotesColor.ORANGE)

        binding.btnSaveNotes.setOnClickListener {
            if (binding.editTitle.text.isEmpty() || binding.editSubTitle.text.isEmpty()) {
                Toast.makeText(requireContext(), "Field is Empty", Toast.LENGTH_LONG).show()

            } else {
                createNotes(it)
            }
            hideKeyboard()
        }

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

    private fun createNotes(it: View?) {
        val title = binding.editTitle.text.toString()
        val subTitle = binding.editSubTitle.text.toString()

        val data = Notes(
            null,
            title,
            subTitle,
            backgroundColor
        )
        viewModel.addNotes(data)
        Toast.makeText(requireContext(), "Notes Save Successfully", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(it!!).navigate(R.id.action_createNoteFragment_to_homeFragment)
    }

    private fun setNoteColor(color: NotesColor) {
        backgroundColor = color
        binding.createNoteFragment.setBackgroundResource(backgroundColor.color)
    }

}