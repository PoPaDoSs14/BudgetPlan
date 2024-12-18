package com.example.budgetplan.presentation

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
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
        viewModel = StatisticsViewModel(application, this)
        viewModel.getUser()
        setContentView(binding.root)


        viewModel.income.observe(this){
            binding.pieChart.income = it
        }

        viewModel.expenses.observe(this){
            binding.pieChart.expenses = it
        }

        binding.remainingMoney.text = "Загрузка..."

        adapter = TaskAdapter()
        binding.operationsList.adapter = adapter
        binding.operationsList.layoutManager = LinearLayoutManager(this)

        viewModel.tasks.observe(this, { taskList ->
            adapter.submitList(taskList)
        })

        viewModel.remaingMoney.observe(this) { moneyText ->
            binding.remainingMoney.text = moneyText
        }

        binding.remainingMoney.setOnClickListener {
            showInputDialog()
        }

        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Введите сумму денег")

        val input = EditText(this).apply {
            inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED
            hint = "Введите сумму (можно отрицательное значение)"
        }
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, which ->
            val enteredValue = input.text.toString()
            if (enteredValue.isNotEmpty()) {
                val moneyValue = enteredValue.toDoubleOrNull()
                if (moneyValue != null) {
                    viewModel.addRemaingMoney(moneyValue.toInt().toString())
                    viewModel.updateMoney(moneyValue.toInt())
                    Toast.makeText(this, "Вы ввели: $moneyValue", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Пожалуйста, введите корректное число", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Поле не должно быть пустым", Toast.LENGTH_SHORT).show()
            }
        }
        builder.setNegativeButton("Отмена") { dialog, which -> dialog.cancel() }

        builder.show()
    }
}