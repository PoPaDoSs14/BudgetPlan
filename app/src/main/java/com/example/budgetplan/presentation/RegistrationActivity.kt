package com.example.budgetplan.presentation

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityMainBinding
import com.example.budgetplan.di.ApplicationComponent
import com.example.budgetplan.domain.User
import dagger.internal.DaggerGenerated
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: RegistrationViewModel

    private val component = DaggerApplicationComponent().create

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.firstLogin(this)

        binding.registerButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val username = binding.username.text.toString().trim()

        when {
            username.isEmpty() -> {
                showToast("Введите имя")
            }
            else -> {
                val user = User(0, username, 0, 0, 0, null)
                addUser(user)
            }
        }
    }

    private fun addUser(user: User) {
        try {
            viewModel.addUser(user)
            navigateToStatistics()
        } catch (e: Exception) {
            showToast("Ошибка при добавлении пользователя: ${e.message}")
        }
    }

    private fun navigateToStatistics() {
        Intent(this, StatisticsActivity::class.java).also {
            startActivity(it)
        }
        finish()
    }

    private fun showToast(message: String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }
}