package com.example.budgetplan.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityMainBinding

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}