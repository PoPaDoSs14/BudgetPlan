package com.example.budgetplan.presentation

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityStatisticsBinding


class StatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticsBinding
    private lateinit var viewModel: StatisticsViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = StatisticsViewModel(application)

        setupObservers()
        setupRecyclerView()
        setupClickListeners()

        viewModel.getUser()
        binding.remainingMoney.text = "Загрузка..."
    }

    private fun setupObservers() {
        viewModel.user.observe(this) { user ->
            user?.let {
                viewModel.loadIncome()
                viewModel.loadExpenses()
            }
        }
        viewModel.income.observe(this) { binding.pieChart.income = it }
        viewModel.expenses.observe(this) { binding.pieChart.expenses = it }
        viewModel.tasks.observe(this) { adapter.submitList(it) }
        viewModel.remaingMoney.observe(this) { binding.remainingMoney.text = it }
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter()
        binding.operationsList.adapter = adapter
        binding.operationsList.layoutManager = LinearLayoutManager(this)
    }

    private fun setupClickListeners() {
        binding.remainingMoney.setOnClickListener { showInputDialog() }
        binding.addTaskButton.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    private fun showInputDialog() {
        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
            hint = "Введите сумму (можно отрицательное значение)"
        }

        AlertDialog.Builder(this)
            .setTitle("Введите сумму денег")
            .setView(input)
            .setPositiveButton("OK") { _, _ -> handleInput(input) }
            .setNegativeButton("Отмена") { dialog, _ -> dialog.cancel() }
            .show()
    }

    private fun handleInput(input: EditText) {
        val enteredValue = input.text.toString()
        if (enteredValue.isNotEmpty()) {
            val moneyValue = enteredValue.toDoubleOrNull()
            if (moneyValue != null) {
                viewModel.addRemaingMoney(moneyValue.toInt().toString())
                viewModel.updateMoney(moneyValue.toInt())
                showToast("Вы ввели: $moneyValue")
            } else {
                showToast("Пожалуйста, введите корректное число")
            }
        } else {
            showToast("Поле не должно быть пустым")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}