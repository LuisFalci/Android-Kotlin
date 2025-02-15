package com.example.a16_room.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a16_room.R
import com.example.a16_room.databinding.ActivityEditStudentBinding
import com.example.a16_room.databinding.ActivityMainBinding
import com.example.a16_room.ui.viewmodels.MainViewModel

class EditStudentActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private var studentId: Int = -1
    private lateinit var binding: ActivityEditStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        if (intent.hasExtra("student_id")) {
            studentId = intent.getIntExtra("student_id", -1)
        }
        if (studentId > 0) {
            viewModel.get(studentId)
            viewModel.student.observe(this) { student ->
                studentId = student.id
                binding.editName.setText(student.name)
                binding.editRegistration.setText(student.registration)
            }
        }

        binding.buttonEdit.setOnClickListener {
            val name = binding.editName.text.toString()
            val registration = binding.editRegistration.text.toString()
            viewModel.update(studentId, name, registration)

            finish()
        }

    }
}