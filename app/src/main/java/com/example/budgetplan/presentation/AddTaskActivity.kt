package com.example.budgetplan.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityAddTaskBinding
import com.example.budgetplan.databinding.ActivityStatisticsBinding
import com.example.budgetplan.domain.Task
import com.example.budgetplan.domain.TaskType

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val viewModel: AddTaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        binding.addButton.setOnClickListener { handleAddTask() }
    }

    private fun setupSpinner() {
        val taskTypes = TaskType.getValues()
        val adapter = TaskTypeAdapter(this, taskTypes)
        binding.typeSpinner.adapter = adapter
    }

    private fun handleAddTask() {
        val valueInput = binding.valueEditText.text.toString()

        if (valueInput.isEmpty()) {
            showToast("Введите значение задачи.")
            return
        }

        val value = valueInput.toIntOrNull()
        if (value == null) {
            showToast("Пожалуйста, введите корректное число.")
            return
        }

        val type = binding.typeSpinner.selectedItem.toString()
        val task = Task(0, viewModel.isProfit(value), value, viewModel.getTaskType(type))

        viewModel.addTask(task)

        showToast("Задача добавлена!")
        navigateToStatistics()
    }

    private fun navigateToStatistics() {
        val intent = Intent(this, StatisticsActivity::class.java)
        startActivity(intent)
        finish() // Закрываем текущую активность
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}