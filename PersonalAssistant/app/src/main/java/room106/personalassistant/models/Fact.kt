package room106.personalassistant.models

import android.content.Context
import room106.personalassistant.FunctionBlockView
import room106.personalassistant.R

class Fact(val id: String, val text: String) {

    fun generateFunctionBlockView(context: Context): FunctionBlockView {
        return FunctionBlockView(context, R.drawable.ic_question_black, text, null)
    }
}