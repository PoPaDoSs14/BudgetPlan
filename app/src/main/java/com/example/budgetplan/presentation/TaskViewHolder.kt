package com.example.budgetplan.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplan.R
import com.example.budgetplan.databinding.ItemTaskBinding
import com.example.budgetplan.domain.Task

class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(task: Task) {
        binding.valueTextView.text = task.value.toString()
        binding.typeTextView.text = task.type.name
        binding.profitTextView.text = if (task.isProfit) "Прибыль" else "Расход"
    }
}