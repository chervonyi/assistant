package room106.personalassistant

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import room106.personalassistant.models.*
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

    fun addRandomBook() {
        db.collection("books")
            .get()
            .addOnSuccessListener {booksResult ->
                val books = ArrayList<Book>()

                // Read ALL books:
                for (document in booksResult) {
                    val data = document.data
                    val title = data["title"] as String
                    val author = data["author"] as String
                    books.add(Book(document.id, title, author))
                }

                val userDocument = db.collection("users").document(USER_ID)

                userDocument.get().addOnSuccessListener { userResult ->
                    if (userResult != null) {
                        // User has been found

                        // Read user flow:
                        val flow = userResult.get("flow") as ArrayList<*>

                        // Remove books that has been found in user current flow (to avoid possible duplicates):
                        val toRemove: MutableList<Book> = ArrayList()
                        for (book in books) {
                            if (flow.contains(book.id)) {
                                toRemove.add(book)
                            }
                        }
                        books.removeAll(toRemove)

                        // Select some random book
                        if (books.size > 0) {
                            val randomBook = books[Random.nextInt(books.size)]

                            // Append user flow with selected random book (Database):
                            userDocument.update("flow", FieldValue.arrayUnion(randomBook.id))

                            // Add new FunctionBlockView to flow
                            val blockView = FunctionBlockView(context, R.drawable.ic_book_black, randomBook.title, randomBook.author)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }
                    } else {
                        Log.d(TAG, "User not found")
                    }
                }
            }
    }

    fun addRandomShow() {
        db.collection("shows")
            .get()
            .addOnSuccessListener {showsResult ->
                val shows = ArrayList<Show>()

                // Read ALL shows:
                for (document in showsResult) {
                    val data = document.data
                    val title = data["title"] as String
                    val description = data["description"] as String
                    shows.add(Show(document.id, title, description))
                }

                val userDocument = db.collection("users").document(USER_ID)

                userDocument.get().addOnSuccessListener { userResult ->
                    if (userResult != null) {
                        // User has been found

                        // Read user flow:
                        val flow = userResult.get("flow") as ArrayList<*>

                        // Remove shows that has been found in user current flow (to avoid possible duplicates):
                        val toRemove: MutableList<Show> = ArrayList()
                        for (show in shows) {
                            if (flow.contains(show.id)) {
                                toRemove.add(show)
                            }
                        }
                        shows.removeAll(toRemove)

                        // Select some random show
                        if (shows.size > 0) {
                            val randomShow = shows[Random.nextInt(shows.size)]

                            // Append user flow with selected random show (Database):
                            userDocument.update("flow", FieldValue.arrayUnion(randomShow.id))

                            // Add new FunctionBlockView to flow
                            val blockView = FunctionBlockView(context, R.drawable.ic_show_black, randomShow.title, randomShow.description)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                    } else {
                        Log.d(TAG, "User not found")
                    }
                }
            }
    }

    fun addRandomFact() {
        db.collection("facts")
            .get()
            .addOnSuccessListener {factsResult ->
                val facts = ArrayList<Fact>()

                // Read ALL facts:
                for (document in factsResult) {
                    val data = document.data
                    val text = data["text"] as String
                    facts.add(Fact(document.id, text))
                }

                val userDocument = db.collection("users").document(USER_ID)

                userDocument.get().addOnSuccessListener { userResult ->
                    if (userResult != null) {
                        // User has been found

                        // Read user flow:
                        val flow = userResult.get("flow") as ArrayList<*>

                        // Remove facts that has been found in user current flow (to avoid possible duplicates):
                        val toRemove: MutableList<Fact> = ArrayList()
                        for (fact in facts) {
                            if (flow.contains(fact.id)) {
                                toRemove.add(fact)
                            }
                        }
                        facts.removeAll(toRemove)

                        // Select some random fact
                        if (facts.size > 0) {
                            val randomFact = facts[Random.nextInt(facts.size)]

                            // Append user flow with selected random fact (Database):
                            userDocument.update("flow", FieldValue.arrayUnion(randomFact.id))

                            // Add new FunctionBlockView to flow
                            val blockView = FunctionBlockView(context, R.drawable.ic_question_black, randomFact.text, null)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                    } else {
                        Log.d(TAG, "User not found")
                    }
                }
            }
    }

    fun addRandomMovie() {
        db.collection("movies")
            .get()
            .addOnSuccessListener {moviesResult ->
                val movies = ArrayList<Movie>()

                // Read ALL movies:
                for (document in moviesResult) {
                    val data = document.data
                    val title = data["title"] as String
                    val description = data["description"] as String
                    movies.add(Movie(document.id, title, description))
                }

                val userDocument = db.collection("users").document(USER_ID)

                userDocument.get().addOnSuccessListener { userResult ->
                    if (userResult != null) {
                        // User has been found

                        // Read user flow:
                        val flow = userResult.get("flow") as ArrayList<*>

                        // Remove movies that has been found in user current flow (to avoid possible duplicates):
                        val toRemove: MutableList<Movie> = ArrayList()
                        for (movie in movies) {
                            if (flow.contains(movie.id)) {
                                toRemove.add(movie)
                            }
                        }
                        movies.removeAll(toRemove)

                        // Select some random movie
                        if (movies.size > 0) {
                            val randomMovie = movies[Random.nextInt(movies.size)]

                            // Append user flow with selected random movie (Database):
                            userDocument.update("flow", FieldValue.arrayUnion(randomMovie.id))

                            // Add new FunctionBlockView to flow
                            val blockView = FunctionBlockView(context, R.drawable.ic_movie_black, randomMovie.title, randomMovie.description)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                    } else {
                        Log.d(TAG, "User not found")
                    }
                }
            }
    }
}