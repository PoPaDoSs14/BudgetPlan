package com.example.budgetplan.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
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
    private lateinit var viewModel: AddTaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        viewModel = AddTaskViewModel(application)
        setContentView(binding.root)

        val taskTypes = TaskType.getValues()

        val adapter = TaskTypeAdapter(this, taskTypes)
        binding.typeSpinner.adapter = adapter


        binding.addButton.setOnClickListener {
            val value = binding.valueEditText.text.toString().toInt()
            val type = binding.typeSpinner.selectedItem.toString()
            val task = Task(0, viewModel.isProfit(value), value, viewModel.getTaskType(type))

            viewModel.addTask(task)

            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }
    }
}