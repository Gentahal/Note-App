package com.abdi.noteapp.utils

import android.view.View
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.abdi.noteapp.R
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.data.entity.Priority
import com.abdi.noteapp.ui.home.HomeFragment
import com.abdi.noteapp.ui.home.HomeFragmentDirections
import com.google.android.material.card.MaterialCardView

object BindingAdapter {

    @BindingAdapter("android:emptyDatabase")
    @JvmStatic
    fun emptyDatabase(view: View, emptyDatabase: MutableLiveData<Boolean>){
        when(emptyDatabase.value){ //pake value agar data ke panggil
            true -> view.visibility = View.VISIBLE
            else -> view.visibility = View.INVISIBLE
        }
    }

    @BindingAdapter("android:parsePriorityColor")
    @JvmStatic
    fun parsePriorityColor(cardView: MaterialCardView, priority: Priority){
        when(priority){
            Priority.HIGH ->{
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.pink))
            }
            Priority.MEDIUM ->{
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.yellow))
            }
            Priority.LOW ->{
                cardView.setCardBackgroundColor(cardView.context.getColor(R.color.green))
            }
        }
    }

    @BindingAdapter("android:sendDataToDetail")
    @JvmStatic
    fun sendDataToDetail(view: ConstraintLayout, currntItem : Notes){
        view.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(currntItem)
            view.findNavController().navigate(action)
        }
    }

    @BindingAdapter("android:parsePriorityToInt")
    @JvmStatic
    fun parsePriorityToInt(view: Spinner, priority: Priority){
        when(priority){
            Priority.HIGH ->{
                view.setSelection(0)
            }
            Priority.MEDIUM ->{
                view.setSelection(1)
            }
            Priority.LOW ->{
                view.setSelection(2)
            }
        }
    }
}