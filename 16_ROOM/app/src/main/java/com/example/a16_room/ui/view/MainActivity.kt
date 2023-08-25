package com.example.a16_room.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a16_room.ui.adapters.StudentAdapter
import com.example.a16_room.ui.listeners.OnStudentListener
import com.example.a16_room.ui.viewmodels.MainViewModel
import com.example.a16_room.databinding.ActivityMainBinding
import com.example.a16_room.ui.listeners.ClickSource

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

        val Intent = Intent(this, EditStudentActivity::class.java)
        val listener = object : OnStudentListener {
            override fun OnClick(id: Int, source: ClickSource) {
                when (source) {
                    ClickSource.TEXT -> {
                        Intent.putExtra("student_id", id)
                        startActivity(Intent)
                    }

                    ClickSource.BUTTON_REMOVE -> {
                        viewModel.delete(id)
                    }
                }
            }
        }


        binding.buttonNewStudent.setOnClickListener {
            val Intent = Intent(this, CreateStudentActivity::class.java)
            startActivity(Intent)
        }

        adapter.attachListener(listener)

        viewModel.getAll()
        observe()
    }

    //Garante a atualização da lista quando volta da edição/criação
    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun observe() {
        viewModel.students.observe(this) {
            adapter.updateStudents(it)
        }
        viewModel.newChange.observe(this) {
            viewModel.getAll()
        }
    }
}