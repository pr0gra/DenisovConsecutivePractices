package com.oolexander.rickandmorty.platform.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.oolexander.rickandmorty.data.models.data.UserInfo
import com.oolexander.rickandmorty.platform.ReminderReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Inject
import java.util.Calendar

class AlarmScheduler @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {

    fun schedule(userInfo: UserInfo) {
        val parts = userInfo.favoriteTime.split(":")
        val hour = parts[0].toInt()
        val minute = parts[1].toInt()

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        if (calendar.timeInMillis <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        val triggerTime = calendar.timeInMillis

        val intent = Intent(context, ReminderReceiver::class.java)
            .putExtra("username", userInfo.name)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1001,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(AlarmManager::class.java)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S &&
            !alarmManager.canScheduleExactAlarms()
        ) {
            return
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    }
}