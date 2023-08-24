package com.example.a16_room

import androidx.recyclerview.widget.RecyclerView
import com.example.a16_room.databinding.RowStudentBinding

class StudentViewHolder(
    private val bind: RowStudentBinding,
    private val listener: OnStudentListener
) : RecyclerView.ViewHolder(bind.root) {

    fun bind(student: StudentModel){
        bind.textStudent.text = student.name

        bind.textStudent.setOnClickListener{
            listener.OnClick(student.id)
        }
    }
}