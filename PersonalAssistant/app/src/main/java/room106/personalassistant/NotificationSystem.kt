package room106.personalassistant

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat


class NotificationSystem(private val context: Context) {

    private val chanelId = "PersonalAssistantNotifications"

    private var builder: NotificationCompat.Builder? = null
    private var notificationId = 1

    init {
        createNotificationChannel()
    }

    fun showNotification(text: String) {
        builder = NotificationCompat.Builder(context, chanelId)
            .setSmallIcon(R.drawable.ic_reminder)
            .setColor(ContextCompat.getColor(context, R.color.reminderColor))
            .setContentTitle("Reminder")
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(PendingIntent.getActivity(context, 0, Intent(), 0));

        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, builder!!.build())
            notificationId++
        }
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notification Chanel"
            val descriptionText = "Personal Assistant Chanel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(chanelId, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager =
                (context as MainActivity).getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}