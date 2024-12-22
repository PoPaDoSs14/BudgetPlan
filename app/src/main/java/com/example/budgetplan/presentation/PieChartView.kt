package com.example.budgetplan.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View

class PieChartView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint().apply {
        isAntiAlias = true
    }
    private val rect = RectF()

    var expenses = DEFAULT_EXPENSES
        set(value) {
            field = value
            invalidate()
        }

    var income = DEFAULT_INCOME
        set(value) {
            field = value
            invalidate()
        }

    var expenseColor = Color.RED
    var incomeColor = Color.GREEN

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = Math.min(width, height) / 2 * 0.8f

        rect.set(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius)

        val total = expenses + income
        Log.d("PieChart", "Expenses: $expenses, Income: $income, Total: $total")

        if (total > 0) {
            drawSlice(canvas, expenseColor, 0f, (expenses / total) * 360)
            drawSlice(canvas, incomeColor, (expenses / total) * 360, (income / total) * 360)
        }
    }

    private fun drawSlice(canvas: Canvas, color: Int, startAngle: Float, sweepAngle: Float) {
        paint.color = color
        canvas.drawArc(rect, startAngle, sweepAngle, true, paint)
    }

    companion object {
        const val DEFAULT_EXPENSES = 70f
        const val DEFAULT_INCOME = 30f
    }
}