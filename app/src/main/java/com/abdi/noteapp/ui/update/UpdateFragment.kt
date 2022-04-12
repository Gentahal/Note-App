package com.abdi.noteapp.ui.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abdi.noteapp.R
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.databinding.FragmentUpdateBinding
import com.abdi.noteapp.ui.NotesViewModel
import com.abdi.noteapp.utils.Extension.setActionBar
import com.abdi.noteapp.utils.HelperFunctions.parseToPriority
import com.abdi.noteapp.utils.HelperFunctions.spinnerListener
import java.text.SimpleDateFormat
import java.util.*

class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding

    private val navArgs: UpdateFragmentArgs by navArgs()

    private val updateViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.safeArgs = navArgs
        binding.toolbarUpdate.setActionBar(requireActivity())

        setHasOptionsMenu(true)

        binding.apply {
            toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener =
                spinnerListener(context, binding.priorityIndicator)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
        val action = menu.findItem(R.id.menu_save)
        action.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            updateNote()
        }
    }

    private fun updateNote() {
        val title = binding.edtTitleUpdate.text.toString()
        val desc = binding.edtDescriptionUpdate.text.toString()
        val priority = binding.spinnerPrioritiesUpdate.selectedItem.toString()
        val date = Calendar.getInstance().time


        val formatDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(date)
        val note = Notes(
            navArgs.currentItem.id,
            title,
            parseToPriority(priority, context),
            desc,
            formatDate
        )

        if (title.isEmpty()) {
            binding.edtTitleUpdate.error = "Please Fill Field"
        } else if (desc.isEmpty()) {
            Toast.makeText(context, "Your desc still empty!!", Toast.LENGTH_LONG).show()
        } else {
            updateViewModel.updateNote(note)
            val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(note)
            findNavController().navigate(action)
            Toast.makeText(context, "Note has been update", Toast.LENGTH_SHORT).show()
        }

    }
}