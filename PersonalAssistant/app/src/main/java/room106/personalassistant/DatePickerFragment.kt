package room106.personalassistant

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        val datePicker = DatePickerDialog(context!!, onDateSetListener, year, month, day)

        // Disable past dates:
        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000

        return datePicker
    }

    private var onDateSetListener: DatePickerDialog.OnDateSetListener? = null

    fun setOnDateSetListener(listener: DatePickerDialog.OnDateSetListener) {
        onDateSetListener = listener
    }

}