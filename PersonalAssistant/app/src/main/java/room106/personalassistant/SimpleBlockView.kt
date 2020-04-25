package room106.personalassistant

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class SimpleBlockView: LinearLayout {

    //region Constructors
    constructor(context: Context?) : super(context) {
        initializeView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initializeView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeView(context)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initializeView(context)
    }
    //endregion

    private lateinit var blockImageView: ImageView
    private lateinit var blockTitleTextView: TextView
    private lateinit var blockMainTextView: TextView

    private fun initializeView(context: Context?) {
        View.inflate(context, R.layout.simple_activity_block_layout, this)

        // Assign views
        blockImageView = findViewById(R.id.blockImage)
        blockTitleTextView = findViewById(R.id.blockTitleTextView)
        blockMainTextView = findViewById(R.id.blockMainTextView)
    }

    constructor(context: Context?,
                imageId: Int,
                imageBackgroundId: Int,
                colorId: Int,
                title: String,
                mainText: String) : super(context) {
        initializeView(context)

        blockImageView.setImageResource(imageId)
        blockImageView.setBackgroundResource(imageBackgroundId)
        blockTitleTextView.text = title
        blockTitleTextView.setTextColor(ContextCompat.getColor(context!!, colorId))
        blockMainTextView.text = mainText
    }
}