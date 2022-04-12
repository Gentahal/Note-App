package com.abdi.noteapp.utils

import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import com.abdi.noteapp.R
import com.abdi.noteapp.data.entity.Notes
import com.abdi.noteapp.data.entity.Priority
import com.google.android.material.card.MaterialCardView

object HelperFunctions {

    fun spinnerListener(
        context: Context?,
        priorityIndicator: MaterialCardView
    ): AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                context?.let {
                    when (position) {
                        0 -> {
                            //Untuk menentukan warna
                            val pink = context.getColor(R.color.pink)
                            priorityIndicator.setCardBackgroundColor(pink)
                        }
                        1 -> {
                            val yellow = context.getColor(R.color.yellow)
                            priorityIndicator.setCardBackgroundColor(yellow)
                        }
                        2 -> {
                            val green = context.getColor(R.color.green)
                            priorityIndicator.setCardBackgroundColor(green)
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

    fun parseToPriority(priority: String, context: Context?): Priority {
        val expectedPriority = context?.resources?.getStringArray(R.array.priorities)
        return when (priority) {
            expectedPriority?.get(0) -> Priority.HIGH
            expectedPriority?.get(1) -> Priority.MEDIUM
            expectedPriority?.get(2) -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    val emptyDataBase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIsDataEmpty(data: List<Notes>?) {
        emptyDataBase.value = data.isNullOrEmpty()
    }
}