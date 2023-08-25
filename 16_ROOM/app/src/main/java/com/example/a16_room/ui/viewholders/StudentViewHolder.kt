package com.example.a16_room.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.a16_room.ui.listeners.OnStudentListener
import com.example.a16_room.data.models.StudentModel
import com.example.a16_room.databinding.RowStudentBinding
import com.example.a16_room.ui.listeners.ClickSource

class StudentViewHolder(
    private val bind: RowStudentBinding,
    private val listener: OnStudentListener
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(student: StudentModel){
        bind.textStudent.text = student.name

        bind.textStudent.setOnClickListener{
            listener.OnClick(student.id, ClickSource.TEXT)
        }
        bind.buttonRemove.setOnClickListener{
            listener.OnClick(student.id, ClickSource.BUTTON_REMOVE)
        }
    }
}