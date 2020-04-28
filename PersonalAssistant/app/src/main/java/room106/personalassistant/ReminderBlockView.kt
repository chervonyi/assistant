package room106.personalassistant

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnFocusChangeListener
import android.view.inputmethod.EditorInfo
import android.widget.*
import android.widget.TextView.OnEditorActionListener
import java.text.SimpleDateFormat
import java.util.*


class ReminderBlockView(context: Context) : LinearLayout(context) {

    private var mImageButton: ImageButton
    private lateinit var mMessageEditText: EditText
    private var mHintView: TextView
    private lateinit var mButtonSetTime: Button
    private lateinit var mButtonSetDate: Button

    private var timeSelected = false

    private val MONTHS = ArrayList<String>().apply {
        add("January")
        add("February")
        add("Marh")
        add("April")
        add("May")
        add("June")
        add("July")
        add("August")
        add("September")
        add("October")
        add("November")
        add("December")
    }

    @SuppressLint("DefaultLocale")
    private val onClickSetTimeListener = OnClickListener {
        val timePickerFragment = TimePickerFragment()
        timePickerFragment.setOnTimeSetListener(TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->

            val isPM: Boolean = i >= 12
            mButtonSetTime.text = String.format(
                "%02d:%02d %s",
                if (i == 12 || i == 0) 12 else i % 12,
                i2,
                if (isPM) "pm" else "am"
            )

            timeSelected = true
            checkReminderInfo()
        })

        timePickerFragment.show((context as MainActivity).supportFragmentManager, "timePicker")
    }

    @SuppressLint("SetTextI18n")
    private val onClickSetDateListener = OnClickListener {
        val datePickerFragment = DatePickerFragment()
        datePickerFragment.setOnDateSetListener(DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            val monthName = MONTHS[i2]

            mButtonSetDate.text = "$i3 $monthName"
            checkReminderInfo()
        })

        datePickerFragment.show((context as MainActivity).supportFragmentManager, "timePicker")
    }

    private val onClickReminderSubmitListener = OnClickListener {

        (context as MainActivity).onClickSubmitReminder(mMessageEditText.text.toString(),
                mButtonSetDate.text.toString(),
                mButtonSetTime.text.toString())
    }

    init {
        View.inflate(context, R.layout.reminder_block_layout, this)

        mButtonSetTime = findViewById(R.id.reminderBlockButtonSetTime)
        mButtonSetDate = findViewById(R.id.reminderBlockButtonSetDate)
        mImageButton = findViewById(R.id.reminderBlockImage)
        mMessageEditText = findViewById(R.id.reminderBlockMessageView)
        mHintView = findViewById(R.id.reminderBlockHintView)

        mButtonSetTime.setOnClickListener(onClickSetTimeListener)
        mButtonSetDate.setOnClickListener(onClickSetDateListener)
        mImageButton.setOnClickListener(onClickReminderSubmitListener)

        //region EditText Listeners
        mMessageEditText.onFocusChangeListener =
            OnFocusChangeListener { v, hasFocus ->
                mMessageEditText.isCursorVisible = hasFocus
            }

        mMessageEditText.setOnClickListener {
            mMessageEditText.isCursorVisible = true
        }

        mMessageEditText.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                mMessageEditText.isCursorVisible = false
                
            }
            false
        })
        
        mMessageEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0!!.isNotEmpty()) {
                    mMessageEditText.backgroundTintList = context.resources.getColorStateList(R.color.reminderColor, context.theme)
                } else {
                    mMessageEditText.backgroundTintList = context.resources.getColorStateList(R.color.reminderBlockInactiveColor, context.theme)
                }

                checkReminderInfo()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { }
        })
        //endregion
    }

    private fun checkReminderInfo() {
        var reminderIsReady = true

        if (!timeSelected || mMessageEditText.text.isEmpty()) {
            reminderIsReady = false
        }

        // TODO - Add check if selected date and selected time is not past
        if (timeSelected) {
            // Selected date & time is FUTURE
            mButtonSetTime.setBackgroundResource(R.drawable.reminder_picker_button_active)
        } else {
            // Selected date & time is PAST
            reminderIsReady = false
            mButtonSetTime.setBackgroundResource(R.drawable.reminder_picker_button)
        }

        if (reminderIsReady) {
            mImageButton.setImageResource(R.drawable.ic_reminder_blue)
            mImageButton.alpha = 1f
        } else {
            mImageButton.setImageResource(R.drawable.ic_reminder_black)
            mImageButton.alpha = 0.1f
        }
    }


}