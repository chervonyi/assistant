package room106.personalassistant.models

import android.content.Context
import room106.personalassistant.FunctionBlockView
import room106.personalassistant.R

class Quote(val id: String, val text: String, val author: String) {

    fun generateFunctionBlockView(context: Context): FunctionBlockView {
        return FunctionBlockView(context, R.drawable.ic_quotes_black, text, author)
    }
}