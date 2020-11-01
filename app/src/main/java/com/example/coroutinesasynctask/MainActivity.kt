package com.example.coroutinesasynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var counterTask: CounterTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart.setOnClickListener {
        val no=etTimer.text.toString().toInt()
            counterTask=CounterTask()
        counterTask.execute(no)
        }
        btnStop.setOnClickListener {
            counterTask.cancel(false)
        }
    }
    inner class CounterTask:AsyncTask<Int,Int,String>(){
        override fun doInBackground(vararg params: Int?): String {
         var n=params[0]?: 0
         while(n >= 0){
             if(isCancelled){
                 return "SHIT"
             }
         Thread.sleep(1000)
          publishProgress(n--)
         }
       return "DONE"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            val progress=values[0]?: 0
            tvTimer.text = progress.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            tvTimer.text = result
        }

        override fun onCancelled(result: String?) {
            super.onCancelled(result)
            tvTimer.text = result
        }
    }
}
