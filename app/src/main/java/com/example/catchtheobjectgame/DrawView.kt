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
    private var isGameOver = false

    init {
        paint.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenWidth = w
        screenHeight = h
        resetGame()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isGameOver) {
            drawGameOverScreen(canvas)
            return
        }

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
            isGameOver = true
            invalidate()
            return
        }

        // Check collision
        if (objectY > screenHeight - basketHeight &&
            objectX > basketX &&
            objectX < basketX + basketWidth
        ) {
            score++
            resetObjectPosition()
        }

        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (isGameOver && event.action == MotionEvent.ACTION_DOWN) {
            resetGame()
        } else if (!isGameOver && event.action == MotionEvent.ACTION_MOVE) {
            basketX = event.x - basketWidth / 2
            if (basketX < 0) basketX = 0f
            if (basketX + basketWidth > screenWidth) basketX = (screenWidth - basketWidth)
        }
        return true
    }

    private fun resetObjectPosition() {
        objectY = 0f
        objectX = Random.nextFloat() * (screenWidth - 100)
    }

    private fun resetGame() {
        objectX = Random.nextFloat() * (screenWidth - 100)
        objectY = 0f
        basketX = (screenWidth / 2 - basketWidth / 2)
        score = 0
        isGameOver = false
        invalidate()
    }

    private fun drawGameOverScreen(canvas: Canvas) {
        paint.color = Color.BLACK
        paint.textSize = 80f
        canvas.drawText("Game Over", (screenWidth / 2 - 200).toFloat(), (screenHeight / 2).toFloat(), paint)
        paint.textSize = 60f
        canvas.drawText("Tap to Restart", (screenWidth / 2 - 180).toFloat(), (screenHeight / 2 + 100).toFloat(), paint)
    }
}
