package com.example.a16_room.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a16_room.R
import com.example.a16_room.databinding.ActivityCreateStudentBinding
import com.example.a16_room.ui.viewmodels.MainViewModel

class CreateStudentActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityCreateStudentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()
            val registration = binding.editRegistration.text.toString()

            viewModel.insert(name, registration)
            finish()
        }
    }
}