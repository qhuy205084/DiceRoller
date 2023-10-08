package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.view.animation.AnimationUtils
/**
 * Roll the dice and update the screen with the result.
 */
class Dice(private val numSides : Int){
    fun roll() : Int{
        return (1..numSides).random()
    }
}
/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {

    private fun rollDice(diceImage : ImageView){
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()

        val drawableResource = when(diceRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        //Add shaking animation
        shakeImage(diceImage)
        // Update the ImageView with the correct drawable resource ID
        diceImage.setImageResource(drawableResource)

        // Update the content description
        diceImage.contentDescription = diceRoll.toString()
    }

    private fun shakeImage(iv:ImageView){
        //Add shaking animation
        val shake = AnimationUtils.loadAnimation(this,R.anim.shake)
        iv.startAnimation(shake)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declare button id
        val rollButton : Button = findViewById(R.id.roll)

        //Find the ImageView in the layout
        val diceImage1 : ImageView = findViewById(R.id.imageView1)
        val diceImage2 : ImageView = findViewById(R.id.imageView2)
        //set listener
        rollButton.setOnClickListener{
            rollDice(diceImage1)
            rollDice(diceImage2)
        }

        // Do a dice roll when the app starts
        rollDice(diceImage1)
        rollDice(diceImage2)
    }
}