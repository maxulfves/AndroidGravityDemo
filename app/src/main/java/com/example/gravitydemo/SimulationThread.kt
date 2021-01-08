package com.example.gravitydemo

import android.util.Log


class SimulationThread : Thread() {

    val myBall = WreckingBall();
    val wall = Wall();

    var stop = false;

    val rope = Vector2D(3f, 4f);
    val ropeL = 1f;

    val time = 0.01f

    public override fun run(){
        while(!stop){

            myBall.velocity += gravity(time)
            myBall.velocity += tension(time)

            myBall.velocity *= 0.999f

            myBall.position+=myBall.velocity * time

            if(myBall.position.y - myBall.r < 0.5){
                myBall.position.y = 0.5f + myBall.r
                myBall.velocity.y = -myBall.velocity.y * 0.9f
            }

            if(myBall.position.x + myBall.r > wall.x - wall.w * 2 && wall.standing){
                myBall.position.x = (wall.x - wall.w * 2 - myBall.r)

                val e_k = 0.5f * myBall.mass * myBall.velocity.magnitude() * myBall.velocity.magnitude()

                wall.giveImpulse(e_k)

                myBall.velocity.x = -myBall.velocity.x * 0.95f
            }

            if(wall.health <= 0 && wall.h > 0){
                wall.standing = false
                wall.h -= 0.1f
            }


            sleep(10)



        }

    }

    private fun gravity(time:Float):Vector2D {
        return Vector2D(0f, -9.81f) * time;
    }

    private fun tension(time:Float):Vector2D {
        val rope:Vector2D = (myBall.position - rope)

        val delta_len = rope.magnitude() - ropeL

        val k = 1500f

        val force:Vector2D = -rope.unit() * delta_len * k

        //Log.d("TAG", delta_len.toString() + "/" + force.magnitude().toString());

        if(delta_len < 0) return Vector2D(0f, 0f)

        return force / myBall.mass * time;
    }

}