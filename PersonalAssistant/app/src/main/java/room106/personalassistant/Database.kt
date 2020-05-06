package room106.personalassistant

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.iid.FirebaseInstanceId
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

                        val quotesInFlow = ArrayList<String>()
                        for (block  in flow) {
                            if ((block as HashMap<*, *>)["type"] == "quote") {
                                quotesInFlow.add(block["id"] as String)
                            }
                        }

                        val toRemove: MutableList<Quote> = ArrayList()
                        for (quote in quotes) {
                            if (quotesInFlow.contains(quote.id)) {
                                toRemove.add(quote)
                            }
                        }

                        quotes.removeAll(toRemove)

                        // Select some random quote
                        if (quotes.size > 0) {
                            val randomQuote = quotes[Random.nextInt(quotes.size)]

                            // Append user flow with selected random quote (Database):
                            // Append quote ID
//                            userDocument.update("flow", FieldValue.arrayUnion(randomQuote.id))


                            // Append quote REFERENCE
//                            val randomQuoteRef = db.collection("quotes").document(randomQuote.id)
//                            userDocument.update("flow", FieldValue.arrayUnion(randomQuoteRef))

                            // Append quote Map
                            val quoteMap = hashMapOf(
                                "type" to "quote",
                                "id" to randomQuote.id,
                                "quote" to randomQuote.text,
                                "author" to randomQuote.author
                            )
                            userDocument.update("flow", FieldValue.arrayUnion(quoteMap))

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

                        val booksInFlow = ArrayList<String>()
                        for (block in flow) {
                            if ((block as HashMap<*, *>)["type"] == "book") {
                                booksInFlow.add(block["id"] as String)
                            }
                        }

                        val toRemove: MutableList<Book> = ArrayList()
                        for (book in books) {
                            if (booksInFlow.contains(book.id)) {
                                toRemove.add(book)
                            }
                        }
                        books.removeAll(toRemove)

                        // Select some random book
                        if (books.size > 0) {
                            val randomBook = books[Random.nextInt(books.size)]

                            // Append user flow with selected random book (Database):
                            val bookMap = hashMapOf(
                                "type" to "book",
                                "id" to randomBook.id,
                                "title" to randomBook.title,
                                "author" to randomBook.author
                            )
                            userDocument.update("flow", FieldValue.arrayUnion(bookMap))

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

                        val showsInFlow = ArrayList<String>()
                        for (block in flow) {
                            if ((block as HashMap<*, *>)["type"] == "show") {
                                showsInFlow.add(block["id"] as String)
                            }
                        }

                        val toRemove: MutableList<Show> = ArrayList()
                        for (show in shows) {
                            if (showsInFlow.contains(show.id)) {
                                toRemove.add(show)
                            }
                        }
                        shows.removeAll(toRemove)

                        // Select some random show
                        if (shows.size > 0) {
                            val randomShow = shows[Random.nextInt(shows.size)]

                            // Append user flow with selected random show (Database):
                            val showMap = hashMapOf(
                                "type" to "show",
                                "id" to randomShow.id,
                                "title" to randomShow.title,
                                "description" to randomShow.description
                            )
                            userDocument.update("flow", FieldValue.arrayUnion(showMap))

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
                        val factsInFlow = ArrayList<String>()
                        for (block in flow) {
                            if ((block as HashMap<*, *>)["type"] == "fact") {
                                factsInFlow.add(block["id"] as String)
                            }
                        }

                        val toRemove: MutableList<Fact> = ArrayList()
                        for (fact in facts) {
                            if (factsInFlow.contains(fact.id)) {
                                toRemove.add(fact)
                            }
                        }
                        facts.removeAll(toRemove)

                        // Select some random fact
                        if (facts.size > 0) {
                            val randomFact = facts[Random.nextInt(facts.size)]

                            // Append user flow with selected random fact (Database):
                            val factMap = hashMapOf(
                                "type" to "fact",
                                "id" to randomFact.id,
                                "text" to randomFact.text
                            )
                            userDocument.update("flow", FieldValue.arrayUnion(factMap))

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
                        val moviesInFlow = ArrayList<String>()
                        for (block in flow) {
                            if ((block as HashMap<*, *>)["type"] == "movie") {
                                moviesInFlow.add(block["id"] as String)
                            }
                        }

                        val toRemove: MutableList<Movie> = ArrayList()
                        for (movie in movies) {
                            if (moviesInFlow.contains(movie.id)) {
                                toRemove.add(movie)
                            }
                        }
                        movies.removeAll(toRemove)

                        // Select some random movie
                        if (movies.size > 0) {
                            val randomMovie = movies[Random.nextInt(movies.size)]

                            // Append user flow with selected random movie (Database):
                            val movieMap = hashMapOf(
                                "type" to "movie",
                                "id" to randomMovie.id,
                                "title" to randomMovie.title,
                                "description" to randomMovie.description
                            )
                            userDocument.update("flow", FieldValue.arrayUnion(movieMap))

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

    fun readFlow() {

        db.collection("users").document(USER_ID).get().addOnSuccessListener { userResult ->
            if (userResult != null) {
                // User has been found

                // Read user flow
                val flow = userResult.get("flow") as ArrayList<*>

                for (block  in flow) {

                    when ((block as HashMap<*, *>)["type"]) {
                        "quote" -> {
                            val id = block["id"] as String
                            val text = block["quote"] as String
                            val author = block["author"] as String
                            val blockView = Quote(id, text, author).generateFunctionBlockView(context)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                        "book" -> {
                            val id = block["id"] as String
                            val title = block["title"] as String
                            val author = block["author"] as String
                            val blockView = Book(id, title, author).generateFunctionBlockView(context)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                        "show" -> {
                            val id = block["id"] as String
                            val title = block["title"] as String
                            val description = block["description"] as String
                            val blockView = Show(id, title, description).generateFunctionBlockView(context)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                        "fact" -> {
                            val id = block["id"] as String
                            val text = block["text"] as String
                            val blockView = Fact(id, text).generateFunctionBlockView(context)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }

                        "movie" -> {
                            val id = block["id"] as String
                            val title = block["title"] as String
                            val description = block["description"] as String
                            val blockView = Movie(id, title, description).generateFunctionBlockView(context)
                            (context as MainActivity).addFunctionBlockView(blockView)
                        }
                    }
                }
            } else {
                Log.d(TAG, "User not found")
            }
        }
    }

    fun clearFlow() {
        // To remove all items in array you should change type of field as NON-ARRAY (e.g - string), and then call arrayRemove()
        db.collection("users").document(USER_ID).update("flow", "test")
        db.collection("users").document(USER_ID).update("flow", FieldValue.arrayRemove())
    }


}