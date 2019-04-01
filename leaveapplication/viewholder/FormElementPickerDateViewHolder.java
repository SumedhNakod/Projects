package com.example.adroit.leaveapplication.viewholder;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.adroit.leaveapplication.R;
import com.example.adroit.leaveapplication.listener.ReloadListener;
import com.example.adroit.leaveapplication.model.BaseFormElement;
import com.example.adroit.leaveapplication.model.FormElementPickerDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.support.v4.os.LocaleListCompat.create;


/**
 * ViewHolder for DatePicker
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public class FormElementPickerDateViewHolder extends BaseViewHolder implements View.OnClickListener{

    private AppCompatTextView mTextViewTitle;
    private AppCompatEditText mEditTextValue;
    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendarCurrentDate;
    private ReloadListener mReloadListener;
    private BaseFormElement mFormElement;
    private int mPosition;

    public FormElementPickerDateViewHolder(View v, Context context, ReloadListener reloadListener) {
        super(v);
        mTextViewTitle = (AppCompatTextView) v.findViewById(R.id.formElementTitle);
        mEditTextValue = (AppCompatEditText) v.findViewById(R.id.formElementValue);

        mReloadListener = reloadListener;
        mCalendarCurrentDate = java.util.Calendar.getInstance();

    }
    @Override
    public void onClick( View view) {


        }


    @Override
    public void bind(int position, BaseFormElement formElement, final Context context) {
        mFormElement = formElement;
        mPosition = position;

        mDatePickerDialog = new DatePickerDialog(context,
                date,
                mCalendarCurrentDate.get(Calendar.YEAR),
                mCalendarCurrentDate.get(Calendar.MONTH),
                mCalendarCurrentDate.get(Calendar.DAY_OF_MONTH));

        mTextViewTitle.setText(formElement.getTitle());
        mEditTextValue.setText(formElement.getValue());
        mEditTextValue.setHint(formElement.getHint());
        mEditTextValue.setFocusableInTouchMode(false);

        mEditTextValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });

        mTextViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatePickerDialog.show();
            }
        });
    }

    /**
     * setting up date picker dialog listener
     */
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendarCurrentDate.set(Calendar.YEAR, year);
            mCalendarCurrentDate.set(Calendar.MONTH, monthOfYear);
            mCalendarCurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            String myFormatDate = ((FormElementPickerDate) mFormElement).getDateFormat();
            SimpleDateFormat sdfDate = new SimpleDateFormat(myFormatDate, Locale.US);

            String currentValue = mFormElement.getValue();
            String newValue = sdfDate.format(mCalendarCurrentDate.getTime());

            // trigger event only if the value is changed
            if (!currentValue.equals(newValue)) {
                mReloadListener.updateValue(mPosition, newValue);
            }

        }

    };


}
