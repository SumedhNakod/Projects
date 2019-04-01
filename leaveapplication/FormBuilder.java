package com.example.adroit.leaveapplication;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.example.adroit.leaveapplication.listener.OnFormElementValueChangedListener;
import com.example.adroit.leaveapplication.model.BaseFormElement;

import java.util.List;



/** Wrapper class around the adapter to assist in building form

 */

public class FormBuilder {

    private FormAdapter mFormAdapter;

    /**
     * constructor with listener callback for changed values
     * @param context
     * @param recyclerView
     */
    public FormBuilder(Context context, RecyclerView recyclerView, OnFormElementValueChangedListener listener) {
        initializeFormBuildHelper(context, recyclerView, listener);
    }

    /**
     * private method for initializing form build helper
     * @param context
     * @param recyclerView
     * @param listener
     */
    private void initializeFormBuildHelper(Context context, RecyclerView recyclerView, OnFormElementValueChangedListener listener) {

        // initialize form adapter
        this.mFormAdapter = new FormAdapter(context, listener);

        // set up the recyclerview with adapter
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setStackFromEnd(false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mFormAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    /**
     * add list of form elements to be shown
     * @param baseFormElements
     */
    public void addFormElements(List<BaseFormElement> baseFormElements) {
        this.mFormAdapter.addElements(baseFormElements);
    }




}