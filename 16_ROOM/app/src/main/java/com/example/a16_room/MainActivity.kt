package com.example.a16_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a16_room.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = StudentAdapter()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerStudents.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerStudents.adapter = adapter

        val listener = object : OnStudentListener {
            override fun OnClick(id: Int) {
                Toast.makeText(applicationContext, id.toString(), Toast.LENGTH_SHORT).show()
                viewModel.get(id)
            }
        }

        adapter.attachListener(listener)

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()
            val registration = binding.editRegistration.text.toString()

            viewModel.insert(name, registration)

        }
        binding.buttonEdit.setOnClickListener {
            val name = binding.editName.text.toString()
            val registration = binding.editRegistration.text.toString()
            viewModel.update(id, name, registration)
        }
        binding.buttonDelete.setOnClickListener {
            viewModel.delete(id)
        }

        observe()
        viewModel.getAll()
    }

    private fun observe() {
        viewModel.students.observe(this) {
            adapter.updateStudents(it)
        }
        viewModel.student.observe(this) {
            id = it.id
            binding.textId.setText(id.toString())
            binding.editName.setText(it.name)
            binding.editRegistration.setText(it.registration)
        }
        viewModel.newChange.observe(this) {
            viewModel.getAll()
        }
    }
}