package com.example.a16_room.ui.view.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.a16_room.databinding.ActivityCreateStudentBinding
import com.example.a16_room.ui.viewmodels.StudentViewModel

class CreateStudentActivity : AppCompatActivity() {
    private lateinit var viewModel: StudentViewModel
    lateinit var binding: ActivityCreateStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()
            val registration = binding.editRegistration.text.toString()

            viewModel.insert(name, registration)
            finish()
        }
    }
}