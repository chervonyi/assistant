package room106.personalassistant

import android.R.id
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.text.bold


class MainActivity : AppCompatActivity() {

    private lateinit var blockLinearLayout: LinearLayout

    // Database
    private lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blockLinearLayout = findViewById(R.id.blockLinearLayout)

        val block1 = SimpleBlockView(this, R.drawable.ic_reminder, R.drawable.block_image_background_activities, R.color.activitiesColor, "Activities", "Don't forget about your morning exercises.")
        val block2 = SimpleBlockView(this, R.drawable.ic_question, R.drawable.block_image_background_tips, R.color.tipsColor, "Tips", "Don't use your phone before you get off the bed.")

        blockLinearLayout.addView(block1)
        blockLinearLayout.addView(block2)

        database = Database(this)
    }

    fun addFunctionBlockView(blockView: FunctionBlockView) {
        // TODO - add using some animation
        blockLinearLayout.addView(blockView)
    }

    fun onClickQuoteFunctionButton(v: View) {
        database.addRandomQuote()
    }

    fun onClickBookFunctionButton(v: View) {
        val bookBlock = FunctionBlockView(this, R.drawable.ic_book_black, "1984", "George Orwell")
        blockLinearLayout.addView(bookBlock)
    }

    fun onClickShowFunctionButton(v: View) {
        val showBlock = FunctionBlockView(this, R.drawable.ic_show_black, "The Handmaid's Tale", "3 seasons")
        blockLinearLayout.addView(showBlock)
    }

    fun onClickFactFunctionButton(v: View) {
        val factBlock = FunctionBlockView(this, R.drawable.ic_question_black, "Burj Khalifa is the tallest tower in the world and it's one of the top attractions to visit in Dubai.", null)
        blockLinearLayout.addView(factBlock)
    }

    fun onClickMovieFunctionButton(v: View) {
        val movieBlock = FunctionBlockView(this, R.drawable.ic_movie_black, "The Imitation Game", "2014")
        blockLinearLayout.addView(movieBlock)
    }

    fun onClickReminderFunctionButton(v: View) {
        val reminderBlock = ReminderBlockView(this)
        blockLinearLayout.addView(reminderBlock)
    }

    fun onClickSubmitReminder(message: String, date: String, time: String) {
        blockLinearLayout.removeViewAt(blockLinearLayout.childCount - 1)

        val reminderText = "\"$message\" is set up for $date $time"
        val reminderBlock = SimpleBlockView(this, R.drawable.ic_reminder, R.drawable.block_image_background_reminder, R.color.reminderColor, "Reminder", reminderText)

        Handler().postDelayed({
            blockLinearLayout.addView(reminderBlock)
        }, 1000)
    }
}
