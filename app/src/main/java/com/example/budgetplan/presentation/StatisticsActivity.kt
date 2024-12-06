package com.example.budgetplan.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ActivityStatisticsBinding


class StatisticsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStatisticsBinding
    private lateinit var viewModel: StatisticsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        viewModel = StatisticsViewModel(application)
        setContentView(binding.root)


        binding.pieChart.income = viewModel.getIncome().value?: 0f
        binding.pieChart.expenses = viewModel.getExpenses().value?: 0f
    }
}