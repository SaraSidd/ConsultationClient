package com.example.consultation.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;



public class PopUpDateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener{

    TextView selectDate;

    public PopUpDateDialog (View view){
        selectDate = (TextView) view;
    }

    public Dialog onCreateDialog(Bundle bundle){
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth+ "-" + (month+1) + "-" +year;
        selectDate.setText(date);
    }
}
