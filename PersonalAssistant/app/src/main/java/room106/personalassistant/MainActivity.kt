package room106.personalassistant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}
