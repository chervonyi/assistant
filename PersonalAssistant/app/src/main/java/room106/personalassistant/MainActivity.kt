package room106.personalassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    private lateinit var blockLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        blockLinearLayout = findViewById(R.id.blockLinearLayout)

        val block1 = SimpleBlockView(this, R.drawable.ic_reminder, R.drawable.block_image_background_activities, R.color.activitiesColor, "Activities", "Don't forget about your morning exercises.")
        val block2 = SimpleBlockView(this, R.drawable.ic_question, R.drawable.block_image_background_tips, R.color.tipsColor, "Tips", "Don't use your phone before you get off the bed.")

        blockLinearLayout.addView(block1)
        blockLinearLayout.addView(block2)
    }

    fun onClickQuoteFunctionButton(v: View) {
        val quoteBlock = FunctionBlockView(this, R.drawable.ic_quotes_black, "Let everything happen to you\n" +
                "Beauty and terror\n" +
                "Just keep going\n" +
                "No feeling is final", "Rainer Maria Rilke")
        blockLinearLayout.addView(quoteBlock)
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
}
