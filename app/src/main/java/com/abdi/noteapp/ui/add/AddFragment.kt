package com.abdi.noteapp.ui.add

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.abdi.noteapp.R
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.data.entity.Priority
import com.abdi.noteapp.databinding.FragmentAddBinding
import com.abdi.noteapp.ui.NotesViewModel
import com.abdi.noteapp.ui.ViewModelFactory
import com.abdi.noteapp.utils.Extension.setActionBar
import com.abdi.noteapp.utils.HelperFunctions
import com.abdi.noteapp.utils.HelperFunctions.spinnerListener
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding

//    private val addViewModel by viewModels<NotesViewModel>()

    private var _addViewModel: NotesViewModel? = null
    private val addViewModel get() = _addViewModel as NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        _addViewModel = activity?.let { obtainViewModel(it) }

        binding.toolbarAdd.setActionBar(requireActivity())

        binding.spinnerPriorities.onItemSelectedListener = spinnerListener(context, binding.priorityIndicator)
    }

    private fun obtainViewModel(activity: FragmentActivity): NotesViewModel? {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[NotesViewModel::class.java]
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
        val action = menu.findItem(R.id.menu_save)
        action.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNotes()
        }
    }

    private fun insertNotes() {
        binding.apply {
            val title = edtTitle.text.toString()
            val priority = spinnerPriorities.selectedItem.toString()
            val desc = edtDescription.text.toString()

            val calendar = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(calendar)

            val note = Notes(
                0,
                title,
                parseToPriority(priority),
                desc,
                date
            )
            if(edtTitle.text.isEmpty() || edtDescription.text.isEmpty()) {
                edtTitle.error = "Please fill fields."
                edtDescription.error = "Please fill fields."
            }else{
                addViewModel.insertData(note)
                Toast.makeText(context, "Succes add note", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            }
        }
    }

    private fun parseToPriority(priority: String) : Priority{
        val expectedPriority = resources.getStringArray(R.array.priorities)
        return when (priority) {
            expectedPriority[0] -> Priority.HIGH
            expectedPriority[1] -> Priority.MEDIUM
            expectedPriority[2] -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}
