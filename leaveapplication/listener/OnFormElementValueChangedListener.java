package com.example.adroit.leaveapplication.listener;


import com.example.adroit.leaveapplication.model.BaseFormElement;

/**
 * Callback to activity when any data in form adapter is changed
 */

public interface OnFormElementValueChangedListener {

    void onValueChanged(BaseFormElement baseFormElement);

}