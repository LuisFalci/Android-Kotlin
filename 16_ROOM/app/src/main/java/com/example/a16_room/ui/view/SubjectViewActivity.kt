package com.example.a16_room.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.a16_room.databinding.ActivitySubjectViewBinding
import com.example.a16_room.ui.viewmodels.SubjectViewModel

class SubjectViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectViewBinding
    private lateinit var viewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SubjectViewModel::class.java]


        viewModel.get(2)
        viewModel.subject.observe(this) { subject ->
            binding.editName.setText(subject.subjectName)
            binding.teste.setText(subject.subjectName)
        }



        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()

            viewModel.insert(name)
        }
    }

}