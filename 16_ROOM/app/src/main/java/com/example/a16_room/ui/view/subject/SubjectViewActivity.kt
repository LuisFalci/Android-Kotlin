package com.example.a16_room.ui.view.subject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a16_room.databinding.ActivitySubjectViewBinding
import com.example.a16_room.ui.adapters.SubjectAdapter
import com.example.a16_room.ui.listeners.ClickSourceSubject
import com.example.a16_room.ui.listeners.OnSubjectListener
import com.example.a16_room.ui.viewmodels.SubjectViewModel

class SubjectViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectViewBinding
    private lateinit var viewModel: SubjectViewModel
    private val adapter = SubjectAdapter()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SubjectViewModel::class.java]

        binding.recyclerSubjects.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerSubjects.adapter = adapter

        val listener = object : OnSubjectListener {
            override fun OnClick(id: Int, source: ClickSourceSubject) {
                when (source) {
                    ClickSourceSubject.TEXT -> {
                    }
                    ClickSourceSubject.BUTTON_REMOVE -> {
                        viewModel.delete(id)
                    }
                }
            }
        }

        binding.buttonInsert.setOnClickListener {
            val name = binding.editName.text.toString()

            viewModel.insert(name)
        }

        adapter.attachListener(listener)

        viewModel.getAll()
        observe()

    }
    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    private fun observe() {
        viewModel.subjects.observe(this) {
            adapter.updateSubjects(it)
        }
        viewModel.newChange.observe(this) {
            viewModel.getAll()
        }
    }

}