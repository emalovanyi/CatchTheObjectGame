package com.example.catchtheobjectgame

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import kotlin.random.Random

class DrawView(context: Context) : View(context) {
    private val paint = Paint()
    private var objectX = 0f
    private var objectY = 0f
    private var basketX = 0f
    private var basketWidth = 300f
    private var basketHeight = 50f
    private var screenWidth = 0
    private var screenHeight = 0
    private var score = 0

    init {
        paint.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w
        screenHeight = h
        objectX = Random.nextFloat() * (screenWidth - 100)
        objectY = 0f
        basketX = (screenWidth / 2 - basketWidth / 2)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw background
        canvas.drawColor(Color.WHITE)

        // Draw falling object
        paint.color = Color.RED
        canvas.drawCircle(objectX, objectY, 50f, paint)

        // Draw basket
        paint.color = Color.BLUE
        canvas.drawRect(
            basketX,
            screenHeight - basketHeight,
            basketX + basketWidth,
            screenHeight.toFloat(),
            paint
        )

        // Draw score
        paint.color = Color.BLACK
        paint.textSize = 60f
        canvas.drawText("Score: $score", 50f, 100f, paint)

        // Update object position
        objectY += 15
        if (objectY > screenHeight) {
            // Reset object if it falls off the screen
            objectY = 0f
            objectX = Random.nextFloat() * (screenWidth - 100)
        }

        // Check collision
        if (objectY > screenHeight - basketHeight &&
            objectX > basketX &&
            objectX < basketX + basketWidth
        ) {
            score++
            objectY = 0f
            objectX = Random.nextFloat() * (screenWidth - 100)
        }

        // Invalidate to redraw
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_MOVE) {
            basketX = event.x - basketWidth / 2
            if (basketX < 0) basketX = 0f
            if (basketX + basketWidth > screenWidth) basketX = (screenWidth - basketWidth)
            invalidate()
        }
        return true
    }
}
