package com.vaibhavmojidra.androidkotlindemo2workmanagerperiodicworkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.vaibhavmojidra.androidkotlindemo2workmanagerperiodicworkrequest.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val myPeriodicWorker=PeriodicWorkRequest.Builder(MyPeriodicWorker::class.java,16,TimeUnit.MINUTES).build()

        val workManager=WorkManager.getInstance(this)
        workManager.getWorkInfoByIdLiveData(myPeriodicWorker.id).observe(this) {
            val currentTime = Calendar.getInstance().time
            val timeFormat = SimpleDateFormat("HH:mm:ss")
            val formattedTime = timeFormat.format(currentTime)
            binding.periodicRequestLogs.append("Periodic Work Request State: ${it.state.name} & Time : $formattedTime\n\n")
        }

        binding.startPeriodicRequestButton.setOnClickListener{
            workManager.enqueue(myPeriodicWorker)
        }
    }
}