package com.example.a16_room.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a16_room.ui.adapters.StudentAdapter
import com.example.a16_room.ui.listeners.OnStudentListener
import com.example.a16_room.ui.viewmodels.MainViewModel
import com.example.a16_room.databinding.ActivityMainBinding
import com.example.a16_room.ui.listeners.ClickSource
import android.view.MenuItem
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.a16_room.R
import com.example.a16_room.ui.view.fragments.AboutFragment
import com.example.a16_room.ui.view.fragments.HomeFragment
import com.example.a16_room.ui.view.fragments.SettingsFragment
import com.example.a16_room.ui.view.fragments.ShareFragment
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = StudentAdapter()
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var drawerLayout: DrawerLayout
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

//        binding.recyclerStudents.layoutManager = LinearLayoutManager(applicationContext)
//        binding.recyclerStudents.adapter = adapter

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


//        binding.buttonNewStudent.setOnClickListener {
//            val Intent = Intent(this, CreateStudentActivity::class.java)
//            startActivity(Intent)
//        }

        adapter.attachListener(listener)

        viewModel.getAll()
        observe()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment()).commit()
            R.id.nav_roll_call -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SettingsFragment()).commit()
            R.id.nav_graph -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ShareFragment()).commit()
            R.id.nav_spreadsheet -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, AboutFragment()).commit()
            R.id.nav_report -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
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