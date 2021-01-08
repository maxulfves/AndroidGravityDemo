package com.example.gravitydemo

public class Vector2D(var x: Float, var y: Float) {


    operator fun unaryMinus():Vector2D = Vector2D(-x, -y)

    //operator fun Vector2D.minus(other: Vector2D) = Vector2D(x - other.x, y - other.y)
    operator fun Vector2D.plus(other: Vector2D):Vector2D = Vector2D(x + other.x, y + other.y)



    operator fun plusAssign(other: Vector2D) {
        x += other.x
        y += other.y
    }

    operator fun timesAssign(other: Float) {
        x *= other
        y *= other
    }

    operator fun times(value: Float): Vector2D {
        return Vector2D(x * value, y * value)
    }

    operator fun minus(other: Vector2D): Vector2D {
        return Vector2D(x - other.x, y - other.y)
    }

    fun unit(): Vector2D {
        return this/magnitude();
    }

    fun magnitude() : Float {
        return Math.sqrt( (x * x + y * y).toDouble()).toFloat()
    }

    operator fun div(value: Float): Vector2D {
        return Vector2D(x / value, y / value);
    }

    fun set(pos: Vector2D) {
        x = pos.x
        y = pos.y
    }


}