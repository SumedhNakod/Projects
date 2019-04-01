package com.example.adroit.leaveapplication.viewholder;

import android.content.Context;

import com.example.adroit.leaveapplication.listener.FormItemEditTextListener;
import com.example.adroit.leaveapplication.model.BaseFormElement;


/**
 * Base ViewHolder method instance
 * Created by Riddhi - Rudra on 30-Jul-17.
 */

public interface BaseViewHolderInterface {
    FormItemEditTextListener getListener();
    void bind(int position, BaseFormElement formElement, Context context);
}
