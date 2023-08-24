package com.example.a16_room.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.a16_room.databinding.ActivityMainBinding

class EditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}