package room106.personalassistant

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class ReminderFirebaseMessagingService: FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")

        // TODO - save "token" into database like:??
        //        database.collection("users").document(USER_ID).update("token", token)
    }

    companion object {
        private const val TAG = "Token"
    }
}