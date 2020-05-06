package room106.personalassistant

import android.R.id
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId


class MainActivity : AppCompatActivity() {

    private lateinit var blockLinearLayout: LinearLayout

    // Database
    private lateinit var database: Database
    private lateinit var notificationSystem: NotificationSystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blockLinearLayout = findViewById(R.id.blockLinearLayout)

        val block1 = SimpleBlockView(this, R.drawable.ic_reminder, R.drawable.block_image_background_activities, R.color.activitiesColor, "Activities", "Don't forget about your morning exercises.")
        val block2 = SimpleBlockView(this, R.drawable.ic_question, R.drawable.block_image_background_tips, R.color.tipsColor, "Tips", "Don't use your phone before you get off the bed.")

        blockLinearLayout.addView(block1)
//        blockLinearLayout.addView(block2)

        database = Database(this, "zNPLWO7TmYuap4IU3vXN")
        notificationSystem = NotificationSystem(this)

        database.readFlow()


        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                Log.d("Token","Token is: $token")
            })

    }


    fun addFunctionBlockView(blockView: FunctionBlockView) {
        // TODO - add using some animation
        blockLinearLayout.addView(blockView)
    }

    fun onClickQuoteFunctionButton(v: View) {
        database.addRandomQuote()
    }

    fun onClickBookFunctionButton(v: View) {
        database.addRandomBook()
    }

    fun onClickShowFunctionButton(v: View) {
        database.addRandomShow()
    }

    fun onClickFactFunctionButton(v: View) {
        database.addRandomFact()
    }

    fun onClickMovieFunctionButton(v: View) {
        database.addRandomMovie()
    }

    fun onClickReminderFunctionButton(v: View) {
        val reminderBlock = ReminderBlockView(this)
        blockLinearLayout.addView(reminderBlock)
    }

    fun onClickSubmitReminder(message: String, date: String, time: String) {
        blockLinearLayout.removeViewAt(blockLinearLayout.childCount - 1)

        val reminderText = "\"$message\" is set up for $date $time"
        val reminderBlock = SimpleBlockView(this, R.drawable.ic_reminder, R.drawable.block_image_background_reminder, R.color.reminderColor, "Reminder", reminderText)

        notificationSystem.showNotification(message)

        Handler().postDelayed({
            blockLinearLayout.addView(reminderBlock)
        }, 1000)
    }


}
