package iboutsikas.practical.org.cloudenoteapplication.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * Created by Zinadore on 6/3/2017.
 */

public class CustomDatePicker implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private EditText mAttachedField;
    private Context mContext;
    private int mDay;
    private int mMonth;
    private int mYear;

    public CustomDatePicker(Context context, int attachedFieldId) {
        Activity act = (Activity)context;
        this.mAttachedField = (EditText)act.findViewById(attachedFieldId);
        this.mAttachedField.setOnClickListener(this);
        this.mContext = context;

        Calendar c = Calendar.getInstance(TimeZone.getDefault());

        this.mDay = c.get(Calendar.DAY_OF_MONTH);
        this.mMonth = c.get(Calendar.MONTH);
        this.mYear = c.get(Calendar.YEAR);

        this.updateField();
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        this.mDay = dayOfMonth;
        this.mMonth = month;
        this.mYear = year;
        this.updateField();
    }

    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(this.mContext, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void updateField() {
        String str = String.format("%1$d/%2$d/%3$d", this.mDay, this.mMonth + 1, this.mYear);
        this.mAttachedField.setText(str);
    }

    public Date getSelectedDate() {
        return new GregorianCalendar(this.mYear, this.mMonth, this.mDay).getTime();
    }
}
