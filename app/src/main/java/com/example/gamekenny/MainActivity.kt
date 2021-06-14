package com.example.gamekenny

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var score:Int=0
    var time=20
    var imgarray= ArrayList<ImageView>()
    var handler:Handler= Handler()
    var runnable:Runnable= Runnable {  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        score=0;

        //hideimages()
        imgarray= arrayListOf(imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8)
        hideimages()

        object: CountDownTimer(20000,1000){

            override fun onTick(millisUntilFinished: Long) {
                timetext.text="time: "+time
                time--

            }

            override fun onFinish() {
                timetext.text="time up"
                time=20
                score=0
                handler.removeCallbacks(runnable)
                for(i in imgarray)
                    i.visibility=View.INVISIBLE
            }
        }.start()
    }

    fun increasescore(view:View)
    {
        score++
        scoretext.text="score: "+score
    }

    fun hideimages(){
        runnable=object :Runnable{
            override fun run() {
                for(i in imgarray){
                    i.visibility=View.INVISIBLE
                }
                val random= Random()
                val index=random.nextInt(8-0) //for using random indexed kenny
                imgarray[index].visibility=View.VISIBLE
                handler.postDelayed(runnable,1000)

            }

        }
        handler.post(runnable)


    }
}