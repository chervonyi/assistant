package room106.personalassistant

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class FunctionButton: LinearLayout {
    //region Constructors
    constructor(context: Context?) : super(context) {
        initializeView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initializeView(context)
        readAttributes(context, attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeView(context)
        readAttributes(context, attrs)
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

    private lateinit var functionImageButton: ImageView
    private lateinit var functionButtonTitle: TextView

    private fun initializeView(context: Context?) {
        View.inflate(context, R.layout.function_button_layout, this)

        functionImageButton = findViewById(R.id.functionButtonIcon)
        functionButtonTitle = findViewById(R.id.functionButtonTitle)
    }

    private fun readAttributes(context: Context?, attrs: AttributeSet?) {
        context!!.theme.obtainStyledAttributes(
            attrs,
            R.styleable.FunctionButton,
            0, 0).apply {

            try {
                functionImageButton.setImageResource(getResourceId(R.styleable.FunctionButton_icon, 0))
                functionButtonTitle.text = getString(R.styleable.FunctionButton_title)
            } finally {
                recycle()
            }
        }
    }
}