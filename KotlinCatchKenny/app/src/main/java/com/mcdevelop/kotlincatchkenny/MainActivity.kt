package com.mcdevelop.kotlincatchkenny

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var score = 0
    private var imageArray = ArrayList<ImageView>()
    private var iv = ArrayList<Int>()
    private var runnable : Runnable = Runnable { }
    private var handler : Handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addImagesToArrayList()
        iv = arrayListOf(R.drawable.bebe,R.drawable.chef,R.drawable.cartman,R.drawable.garry,
            R.drawable.kenny, R.drawable.kyle,R.drawable.sheila,R.drawable.stan,R.drawable.towelie)
        hideImages()

        //Countdown Timer
        object : CountDownTimer(15500,1000){
            override fun onTick(p0: Long) {
                timeTextView.text = "Time: ${p0/1000}"
            }

            override fun onFinish() {
                score = 0
                timeTextView.text = "Time: $score"

                handler.removeCallbacks(runnable) // stop runnable
                makeImagesInvisible()

                //Alert
                val alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over")
                alert.setMessage("Do you want to restart the game ?")
                alert.setPositiveButton("Yes") { _, _ ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }

                alert.setNegativeButton("No") { _, _ ->
                    Toast.makeText(this@MainActivity, "Game Over", Toast.LENGTH_LONG).show()
                    //finish()
                }
                alert.show()

            }

        }.start()

    }

    private fun addImagesToArrayList(){
        imageArray.add(imageView1)
        imageArray.add(imageView2)
        imageArray.add(imageView3)
        imageArray.add(imageView4)
        imageArray.add(imageView5)
        imageArray.add(imageView6)
        imageArray.add(imageView7)
        imageArray.add(imageView8)
        imageArray.add(imageView9)
    }

    private fun makeImagesInvisible(){
        for(image in imageArray){
            image.visibility = View.INVISIBLE
        }
    }

    private fun hideImages(){
        runnable = object : Runnable {
            override fun run() {

                iv.shuffle() // randomly generate my iv Int ArrayList
                imageView1.setImageResource(iv[1]) //put all the imageViews same image index
                imageView2.setImageResource(iv[1])
                imageView3.setImageResource(iv[1])
                imageView4.setImageResource(iv[1])
                imageView5.setImageResource(iv[1])
                imageView5.setImageResource(iv[1])
                imageView6.setImageResource(iv[1])
                imageView7.setImageResource(iv[1])
                imageView8.setImageResource(iv[1])
                imageView9.setImageResource(iv[1])

                makeImagesInvisible()
                imageArray.random().visibility = View.VISIBLE
                handler.postDelayed(this,500) //delay for 0.5s

            }
        }
        handler.post(runnable) //start runnable

    }

    fun increaseScore(view: View){

        if(iv[1] == R.drawable.kenny)
            score += 5
        else
            score++
        scoreTextView.text = "Score: $score"
    }

}