package com.example.budgetplan.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityMainBinding
import com.example.budgetplan.domain.User

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = RegistrationViewModel(application)


        binding.registerButton.setOnClickListener {
            val name = binding.username.text.toString()
            val user = User(0, name, 0, 0, 0, null)
            viewModel.addUser(user)
        }

    }
}