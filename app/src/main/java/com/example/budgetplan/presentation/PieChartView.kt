package com.example.budgetplan.presentation

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class PieChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val paint = Paint()
    private val rect = RectF()


    var expenses = 70f
    var income = 30f

    init {
        paint.isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = Math.min(width, height) / 2 * 0.8f

        rect.set(width / 2 - radius, height / 2 - radius, width / 2 + radius, height / 2 + radius)


        var startAngle = 0f


        val sweepAngleExpenses = (expenses / (expenses + income)) * 360
        paint.color = Color.RED
        canvas.drawArc(rect, startAngle, sweepAngleExpenses, true, paint)


        startAngle += sweepAngleExpenses


        val sweepAngleIncome = (income / (expenses + income)) * 360
        paint.color = Color.GREEN
        canvas.drawArc(rect, startAngle, sweepAngleIncome, true, paint)
    }
}