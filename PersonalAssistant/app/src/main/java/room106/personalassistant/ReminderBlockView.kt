package room106.personalassistant

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.LinearLayout

class ReminderBlockView: LinearLayout {

    //region Constructors
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initializeView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeView(context)
    }
    //endregion

    private lateinit var buttonSetTime: Button
    private lateinit var buttonSetDate: Button

    private fun initializeView(context: Context?) {
        View.inflate(context, R.layout.reminder_block_layout, this)

        buttonSetTime = findViewById(R.id.reminderBlockButtonSetTime)
        buttonSetDate = findViewById(R.id.reminderBlockButtonSetDate)

        buttonSetTime.setOnClickListener(onClickSetTimeListener)
        buttonSetDate.setOnClickListener(onClickSetDateListener)
    }

    constructor(context: Context?): super(context) {
        initializeView(context)

    }

    private val onClickSetTimeListener = OnClickListener {
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.setOnTimeSetListener(TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            buttonSetTime.text = "$i:$i2"
        })
        timePickerFragment.show((context as MainActivity).supportFragmentManager, "timePicker")
    }

    private val onClickSetDateListener = OnClickListener {
        val datePickerFragment = DatePickerFragment()
        datePickerFragment.setOnDateSetListener(DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            buttonSetDate.text = "$i:$i2:$i3"
        })
        datePickerFragment.show((context as MainActivity).supportFragmentManager, "timePicker")
    }
}