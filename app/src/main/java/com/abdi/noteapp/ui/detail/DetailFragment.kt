package com.abdi.noteapp.ui.detail

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abdi.noteapp.R
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.databinding.FragmentDetailBinding
import com.abdi.noteapp.ui.NotesViewModel
import com.abdi.noteapp.utils.Extension.setActionBar

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding as FragmentDetailBinding

    private val detailViewModel by viewModels<NotesViewModel>()

    private val navArgs by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.safeArgs = navArgs
        binding.toolbarDetail.setActionBar(requireActivity())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit -> {
                val action = DetailFragmentDirections.actionDetailFragmentToUpdateFragment(
                    Notes(
                        navArgs.currentItem.id,
                        navArgs.currentItem.title,
                        navArgs.currentItem.priority,
                        navArgs.currentItem.description,
                        navArgs.currentItem.date
                    )
                )
                findNavController().navigate(action)
            }
            R.id.menu_delete -> confirmDeleteNote()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun confirmDeleteNote() {
        AlertDialog.Builder(context)
            .setTitle("Delete `${navArgs.currentItem.title}`?")
            .setMessage("Are you sure want to remove me?")
            .setPositiveButton("Sure") { _, _ ->
                detailViewModel.deleteNote(navArgs.currentItem)
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
                Toast.makeText(context, "Succes delete note", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Not yet") { _, _ ->
                findNavController().navigate(R.id.action_detailFragment_to_homeFragment)
            }
            .show()
    }
}