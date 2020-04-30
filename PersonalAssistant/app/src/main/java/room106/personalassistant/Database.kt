package room106.personalassistant

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import room106.personalassistant.models.Quote
import kotlin.random.Random

class Database(private val context: Context, val USER_ID: String) {

    private val TAG = "Database"
    private val db = Firebase.firestore

    fun addRandomQuote() {

        db.collection("quotes")
            .get()
            .addOnSuccessListener {quotesResult ->
                val quotes = ArrayList<Quote>()

                // Read ALL quotes:
                for (document in quotesResult) {
                    val data = document.data
                    val text = data["quote"] as String
                    val author = data["author"] as String
                    quotes.add(Quote(document.id, text, author))
                }

                val userDocument = db.collection("users").document(USER_ID)

                userDocument.get().addOnSuccessListener { userResult ->
                    if (userResult != null) {
                        // User has been found

                        // Read user flow:
                        val flow = userResult.get("flow") as ArrayList<*>

                        // Remove quotes that has been found in user current flow (to avoid possible duplicates):
                        val toRemove: MutableList<Quote> = ArrayList()
                        for (quote in quotes) {
                            if (flow.contains(quote.id)) {
                                toRemove.add(quote)
                            }
                        }
                        quotes.removeAll(toRemove)

                        // Select some random quote
                        if (quotes.size > 0) {
                            val randomQuote = quotes[Random.nextInt(quotes.size)]

                            // Append user flow with selected random quote (Database):
                            userDocument.update("flow", FieldValue.arrayUnion(randomQuote.id))

                            // Add new FunctionBlockView to flow
                            val blockView = FunctionBlockView(context, R.drawable.ic_quotes_black, randomQuote.text, randomQuote.author)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                    } else {
                        Log.d(TAG, "User not found")
                    }
                }
            }


    }
}