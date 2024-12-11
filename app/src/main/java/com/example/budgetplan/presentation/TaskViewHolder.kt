package com.example.budgetplan.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetplan.R
import com.example.budgetplan.domain.Task

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val valueTextView: TextView = itemView.findViewById(R.id.valueTextView)
    private val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
    private val profitTextView: TextView = itemView.findViewById(R.id.profitTextView)

    fun bind(task: Task) {
        valueTextView.text = task.value.toString()
        typeTextView.text = task.type.name
        profitTextView.text = if (task.isProfit) "Прибыль" else "Расход"
    }
}