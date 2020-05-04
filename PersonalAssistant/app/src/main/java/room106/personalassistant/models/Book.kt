package room106.personalassistant.models

import android.content.Context
import room106.personalassistant.FunctionBlockView
import room106.personalassistant.R

class Book(val id: String, val title: String, val author: String) {

    fun generateFunctionBlockView(context: Context): FunctionBlockView {
        return FunctionBlockView(context, R.drawable.ic_book_black, title, author)
    }
}