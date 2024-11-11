package com.example.serviceslab

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CountdownService : Service() {


    private var countdownJob: Job? = null

    override fun onStartCommand(Intent: Intent?, flags: Int, startId: Int): Int {
        val countdownTime = Intent?.getIntExtra("countdownTime", 0) ?: 0

        if (countdownTime > 0) {
            startCountdown(countdownTime)
        }

        return START_NOT_STICKY

    }

    private fun startCountdown(time: Int) {
        countdownJob = CoroutineScope(Dispatchers.Default).launch {
            for (i in time downTo 1) {
                Log.d("CountdownService","Countdown: $i")
                delay(1000L)
            }
            stopSelf()
        }

    }

    override fun onDestroy() {
        countdownJob?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


}