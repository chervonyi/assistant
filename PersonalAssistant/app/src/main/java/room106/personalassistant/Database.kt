package room106.personalassistant

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import room106.personalassistant.models.Quote
import kotlin.random.Random

class Database(val context: Context) {

    private val TAG = "Database"
    private val db = Firebase.firestore

    fun addRandomQuote() {

        db.collection("quotes")
            .get()
            .addOnSuccessListener {result ->
                val quotes = ArrayList<Quote>()
                // Read all quotes:
                for (document in result) {
                    val data = document.data
                    val text = data["quote"] as String
                    val author = data["author"] as String
                    quotes.add(Quote(document.id, text, author))
                }

                // Select one random quote:
                if (quotes.size > 0) {
                    val randomQuote = quotes[Random.nextInt(quotes.size)]
                    // TODO - check if this quote is not contain in:
                    //          db.collection("users").equalTo(CURRENT_USER_ID).collection("flow")

                    // Put selected random quote into user's flow:
                    // TODO - add: db.collection("users").equalTo(CURRENT_USER_ID).collection("flow").add(randomQuote)

                    val blockView = FunctionBlockView(context, R.drawable.ic_quotes_black, randomQuote.text, randomQuote.author)
                    (context as MainActivity).addFunctionBlockView(blockView)
                }
            }
    }
}