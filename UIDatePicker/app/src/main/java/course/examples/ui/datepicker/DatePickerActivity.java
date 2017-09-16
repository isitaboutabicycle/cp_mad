package course.examples.ui.datepicker;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

// This application uses some deprecated methods. 
// See UIDatePickerFragmentActivity for a more modern version of this application

public class DatePickerActivity extends Activity {

	private TextView mDateDisplay;
	private TextView mTimeDisplay;
	private Button mPickDate;
	private Button mPickTime;
	private int mYear;
	private int mMonth;
	private int mDay;
	private int mHour;
	private int mMinute;

	static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;

	// The callback received when the user "sets" the date in the Dialog
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDisplay();
		}
	};

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute;
            updateDisplay();
        }
    };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Capture our View elements
		mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
		mPickDate = (Button) findViewById(R.id.pickDate);

        mPickTime = (Button) findViewById(R.id.pickTime);

		// Set an OnClickListener on the Change The Date Button
		mPickDate.setOnClickListener(new View.OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

        mPickTime.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });

		// Get the current date
		final Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);

		// Display the current date
		updateDisplay();
	}

	// Update the date in the TextView
	private void updateDisplay() {
		mDateDisplay.setText(new StringBuilder()
				// Month is 0 based so add 1
				.append(mMonth + 1).append("-").append(mDay).append("-")
				.append(mYear).append(", ").append(pad(mHour)).append(":").append(pad(mMinute)));
	}

	private String pad(final int c) {
        if (c > 10){
            return String.valueOf(c);
        }
        else
        {
            return new StringBuilder().append("0").append(c).toString();
        }
    }

	// Create and return DatePickerDialog
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);

        case TIME_DIALOG_ID:
            return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute, false);
	    }
		return null;
	}
}