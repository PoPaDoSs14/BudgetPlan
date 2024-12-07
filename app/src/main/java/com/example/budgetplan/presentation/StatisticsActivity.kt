package com.example.budgetplan.presentation

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityStatisticsBinding


class StatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticsBinding
    private lateinit var viewModel: StatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        viewModel = StatisticsViewModel(application)
        viewModel.getUser()
        setContentView(binding.root)


        binding.pieChart.income = viewModel.getIncome().value?: 0f
        binding.pieChart.expenses = viewModel.getExpenses().value?: 0f
        binding.remainingMoney.text = "Загрузка..."

        viewModel.remaingMoney.observe(this) { moneyText ->
            binding.remainingMoney.text = moneyText
        }

        binding.remainingMoney.setOnClickListener {
            showInputDialog()
        }
    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Введите сумму денег")

        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK") { dialog, which ->
            val enteredValue = input.text.toString()
            if (enteredValue.isNotEmpty()) {
                val moneyValue = enteredValue.toFloatOrNull()
                if (moneyValue != null) {
                    viewModel.addRemaingMoney(moneyValue.toInt().toString())
                    Toast.makeText(this, "Вы ввели: $moneyValue", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Пожалуйста, введите корректное число", Toast.LENGTH_SHORT).show()
                }
            }
        }
        builder.setNegativeButton("Отмена") { dialog, which -> dialog.cancel() }

        builder.show()
    }
}