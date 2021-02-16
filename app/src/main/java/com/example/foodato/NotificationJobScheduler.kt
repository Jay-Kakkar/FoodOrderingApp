package com.example.foodato

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat


class NotificationJobScheduler : JobService() {
    private lateinit var mNotifyManager: NotificationManager
    private var scheduler: JobScheduler? = null
    private var JobId=0
    private var PRIMARY_CHANNEL_ID = "primary channel notification"

    override fun onStartJob(params: JobParameters?): Boolean {


        notificationChannel()
        var pendingIntent = PendingIntent.getActivity(
            this,
            0, Intent(this, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        Log.e(this.toString(), "11111111111111111")
        var builder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Job Service")
            .setContentText("Order has been placed")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
        mNotifyManager.notify(0, builder.build())
        return false
    }

    private fun notificationChannel() {
        mNotifyManager= getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val notificationChannel=NotificationChannel(
                PRIMARY_CHANNEL_ID,
                "notfication channel name",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor= Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "notification_channel_description"

            mNotifyManager.createNotificationChannel(notificationChannel)
        }

    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
//    var activity: MainActivity? = null
//fun onAttach(activity: Activity?) {
//        this.activity = activity as MainActivity?
//    }
//    fun scheduleJob() {
//        Log.e(this.toString(), "11111111111111111")
//
//        val netWorkGroup = JobInfo.NETWORK_TYPE_UNMETERED
//
//    scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
//
////component name is used to associate Job service with job info
//        var componentName = ComponentName(
//            this,
//            NotificationJobScheduler::class.java.name
//        )
//        Log.e(this.toString(), "333333333333333333${componentName}")
//
//        val builder= JobInfo.Builder(JobId, componentName)
//            .setRequiredNetworkType(netWorkGroup)
//            .setRequiresDeviceIdle(true)
////        builder.setOverrideDeadline(( 1000).toLong());
//
//        Log.e(this.toString(), "22222222222222222222$builder")
//
//
//        val myJobInfo=builder.build()
//        scheduler?.schedule(myJobInfo)
//        Toast.makeText(
//            this, "Job Scheduled, job will run when " +
//                    "the constraints are met.", Toast.LENGTH_SHORT
//        ).show()
//    }
//    fun cancelJobs() {
//        scheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
//        scheduler?.cancelAll()
//        Toast.makeText(this, "jobs cancelled", Toast.LENGTH_SHORT)
//            .show();
//    }
}


