package room106.personalassistant

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class TimePickerFragment: DialogFragment()  {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, onTimeSetListener, hour, minute, DateFormat.is24HourFormat(activity))
    }

    private var onTimeSetListener: TimePickerDialog.OnTimeSetListener? = null

    fun setOnTimeSetListener(listener: TimePickerDialog.OnTimeSetListener) {
        onTimeSetListener = listener
    }
}