package com.example.a16_room.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.a16_room.data.models.SubjectModel
import com.example.a16_room.databinding.RowStudentBinding
import com.example.a16_room.databinding.RowSubjectBinding
import com.example.a16_room.ui.listeners.ClickSourceSubject
import com.example.a16_room.ui.listeners.OnStudentListener
import com.example.a16_room.ui.listeners.OnSubjectListener

class SubjectViewHolder (
    private val bind: RowSubjectBinding,
    private val listener: OnSubjectListener
) : RecyclerView.ViewHolder(bind.root) {
    fun bind(subject: SubjectModel){
            bind.textSubject.text = subject.subjectName

            bind.textSubject.setOnClickListener{
                listener.OnClick(subject.subjectId, ClickSourceSubject.TEXT)
            }
            bind.buttonRemove.setOnClickListener{
                listener.OnClick(subject.subjectId, ClickSourceSubject.BUTTON_REMOVE)
            }
        }
}