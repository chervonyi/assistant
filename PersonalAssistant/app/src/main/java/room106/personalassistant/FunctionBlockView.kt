package room106.personalassistant

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class FunctionBlockView: LinearLayout {

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
    //endregion

    private lateinit var mImage: ImageView
    private lateinit var mMainText: TextView
    private lateinit var mSubText: TextView

    private fun initializeView(context: Context?) {

        View.inflate(context, R.layout.function_block_layout, this)
        mImage = findViewById(R.id.functionBlockImage)
        mMainText = findViewById(R.id.functionBlockMainText)
        mSubText = findViewById(R.id.functionBlockSubText)
    }

    constructor(context: Context?,
                imageId: Int,
                mainText: String,
                subText: String?): super(context) {

        initializeView(context)

        mImage.setImageResource(imageId)
        mMainText.text = mainText

        if (subText == null) {
            mSubText.visibility = View.GONE
        } else {
            mSubText.visibility = View.VISIBLE
            mSubText.text = subText
        }
    }
}