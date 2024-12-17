package com.example.budgetplan.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)
        viewModel.firstLogin(this)

        binding.registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = binding.username.text.toString().trim()

        if (name.isNotEmpty()) {
            val user = User(0, name, 0, 0, 0, null)
            try {
                viewModel.addUser(user)
                navigateToStatistics()
            } catch (e: Exception) {
                Toast.makeText(baseContext, "Ошибка при добавлении пользователя: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(baseContext, "Введите имя", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateToStatistics() {
        val intent = Intent(this, StatisticsActivity::class.java)
        startActivity(intent)
        finish()
    }
}