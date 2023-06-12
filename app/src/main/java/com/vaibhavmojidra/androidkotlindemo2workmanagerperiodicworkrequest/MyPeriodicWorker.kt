package com.vaibhavmojidra.androidkotlindemo2workmanagerperiodicworkrequest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.text.SimpleDateFormat
import java.util.Calendar

class MyPeriodicWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {
        Thread.sleep(5000)
        val currentTime = Calendar.getInstance().time
        val timeFormat = SimpleDateFormat("HH:mm:ss")
        val formattedTime = timeFormat.format(currentTime)

        Log.i("MyTag","Task Completed : $formattedTime")

        return Result.success()
    }

}