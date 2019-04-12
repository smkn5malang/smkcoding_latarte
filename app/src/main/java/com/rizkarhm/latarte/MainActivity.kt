package com.rizkarhm.latarte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class MainActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds

    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, StartActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

//        val progressBar = findViewById<ProgressBar>(R.id.loading) as ProgressBar
//        var barStatus = 0
//        Thread(Runnable {
//            while (barStatus < 100)
//            {
//                barStatus +=1
//                    try {
//                        Thread.sleep(30)
//                        progressBar.setProgress(barStatus)
//                    }catch (exp:InterruptedException){
//                        exp.printStackTrace()
//                    }
//            }
//        }).start()
    }

    public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
