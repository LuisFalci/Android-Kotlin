package com.example.a16_room.ui.view.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.a16_room.databinding.ActivityCreateStudentBinding
import com.example.a16_room.ui.viewmodels.StudentViewModel

class CreateStudentActivity : AppCompatActivity() {
    private lateinit var viewModel: StudentViewModel
    lateinit var binding: ActivityCreateStudentBinding
    private var subjectId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        if (intent.hasExtra("subject_id")) {
            subjectId = intent.getIntExtra("subject_id", -1)
            Toast.makeText(this, subjectId.toString(), Toast.LENGTH_SHORT).show()
        }

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()
            val registration = binding.editRegistration.text.toString()

            viewModel.insert(name, registration)
            finish()
        }
    }
}