package room106.personalassistant.models

import android.content.Context
import room106.personalassistant.FunctionBlockView
import room106.personalassistant.R

class Movie(val id: String, val title: String, val description: String) {

    fun generateFunctionBlockView(context: Context): FunctionBlockView {
        return FunctionBlockView(context, R.drawable.ic_movie_black, title, description)
    }
}