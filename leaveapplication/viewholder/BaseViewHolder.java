package com.example.adroit.leaveapplication.viewholder;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.example.adroit.leaveapplication.R;
import com.example.adroit.leaveapplication.listener.FormItemEditTextListener;
import com.example.adroit.leaveapplication.model.BaseFormElement;


/**
 * Base ViewHolder for all other viewholders
 */

public class BaseViewHolder extends RecyclerView.ViewHolder implements BaseViewHolderInterface {

    AppCompatEditText e1;
    LinearLayout parentLayout;
    public BaseViewHolder(View itemView) {
        super(itemView);
        e1=itemView.findViewById(R.id.formElementValue);
        parentLayout=itemView.findViewById(R.id.parent_layout);

    }

    @Override
    public FormItemEditTextListener getListener() {
        return null;
    }

    @Override
    public void bind(int position, BaseFormElement formElement, Context context) {

    }

}
