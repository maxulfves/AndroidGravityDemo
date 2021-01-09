package com.example.gravitydemo

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View

class SimulationView(context: Context?, thread:SimulationThread) : View(context) {

    val bob = thread;

    var end = 10f;



    override fun onDraw(canvas: Canvas) {

        val ratio:Float = (height.toFloat()/width);
        val matrix = Matrix()

        end = (4.0f / ratio);

        matrix.preScale(width/end, -(height)/4.0f)
        matrix.preTranslate(0f, -4f)

        canvas.setMatrix(matrix)

        super.onDraw(canvas)

        val bg_paint = Paint();
        val ball_paint = Paint();
        val ropePaint = Paint();
        val floorPaint = Paint();

        ropePaint.setColor(Color.BLACK)
        ropePaint.strokeWidth = 0.03f
        bg_paint.setColor(Color.BLUE)
        ball_paint.setColor(Color.rgb(205, 100, 100))
        floorPaint.setColor(Color.YELLOW)

        canvas.drawRect(0.0f, 0.0f, end, 4.0f, bg_paint);

        ball_paint.strokeWidth = 0.1f;

        //canvas.drawPoint(0f, 0f, ball_paint);
        canvas.drawPoint(end, 4f, ball_paint);
        canvas.drawPoint(0f, 4f, ball_paint);
        canvas.drawPoint(end, 0f, ball_paint);

        //canvas.drawLine(-100f, -100f, 100f, 100f, ball_paint);

        val ball = RectF(
            bob.myBall.position.x - bob.myBall.r,
            bob.myBall.position.y-bob.myBall.r,
            bob.myBall.position.x+bob.myBall.r,
            bob.myBall.position.y+bob.myBall.r);

        val wall = RectF(
            bob.wall.x - 0.5f,
            bob.wall.y,
            bob.wall.x + 0.5f,
            bob.wall.y + bob.wall.h
        )

        canvas.drawLine(bob.myBall.position.x, bob.myBall.position.y, bob.rope.x, bob.rope.y, ropePaint)

        canvas.drawRect(0f, 0f, 10f, 0.5f, floorPaint)

        val wallPaint = Paint()
        wallPaint.setColor(Color.rgb((255f * bob.wall.health/100).toInt(), 0, 0))
        canvas.drawRect(wall, wallPaint)

        canvas.drawOval(ball, ball_paint);
        invalidate()
    }



    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event != null) {
            val m_x:Float = event.x / height * 4.0f
            val m_y:Float = (height - event.y) / width * end

            val pos = Vector2D(m_x, m_y)

            Log.d("TAG", m_x.toString() + "/" + m_y.toString())

            bob.myBall.position.set(pos)
            when (event.action){
                MotionEvent.ACTION_DOWN -> {

                    bob.myBall.position.set(pos)
                    bob.myBall.velocity.set(Vector2D(0f, 0f))

                }
                MotionEvent.ACTION_UP -> {

                }
                MotionEvent.ACTION_MOVE -> {

                    bob.myBall.position.set(pos)
                    bob.myBall.velocity.set(Vector2D(0f, 0f))
                }
            }

            performClick()

        }



        return true;//super.onTouchEvent(event)
    }



}