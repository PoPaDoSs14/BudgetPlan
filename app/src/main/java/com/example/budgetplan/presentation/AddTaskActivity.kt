package com.example.budgetplan.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityAddTaskBinding
import com.example.budgetplan.databinding.ActivityStatisticsBinding
import com.example.budgetplan.domain.Task

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addButton.setOnClickListener {
            val value = binding.valueEditText.text.toString().toInt()
            val type = binding.typeSpinner.prompt.toString()
            val task = Task(0, viewModel.isProfit(value), value, viewModel.getTaskType(type))

            viewModel.addTask(task)
        }
    }
}