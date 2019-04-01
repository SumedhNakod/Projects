package com.example.adroit.leaveapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adroit.leaveapplication.listener.OnFormElementValueChangedListener;
import com.example.adroit.leaveapplication.model.BaseFormElement;
import com.example.adroit.leaveapplication.model.FormElementPickerDate;
import com.example.adroit.leaveapplication.model.FormElementTextEmail;
import com.example.adroit.leaveapplication.model.FormElementTextMultiLine;
import com.example.adroit.leaveapplication.model.FormElementTextNumber;
import com.example.adroit.leaveapplication.model.FormElementTextPhone;
import com.example.adroit.leaveapplication.model.FormElementTextSingleLine;
import com.example.adroit.leaveapplication.model.FormHeader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnFormElementValueChangedListener {
    private RecyclerView mRecyclerView;
    private FormBuilder mFormBuilder;
    String s1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.btnclick);

        setupToolBar();

        setupForm();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolBar() {

        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

    }

    private void setupForm() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mFormBuilder = new FormBuilder(this, mRecyclerView, this);

        FormHeader header1 = FormHeader.createInstance("Personal Info");
        FormElementTextEmail element11 = FormElementTextEmail.createInstance().setTitle("Email").setHint("Enter Email");
        FormElementTextPhone element12 = FormElementTextPhone.createInstance().setTitle("Phone").setValue("+91");
        FormElementTextPhone element13 = FormElementTextPhone.createInstance().setTitle("SAP ID").setHint("Enter SAP ID");

        FormHeader header2 = FormHeader.createInstance("Family Info");
        FormElementTextEmail element20 = FormElementTextEmail.createInstance().setTitle("Email(parent)").setHint("Enter Email");
        FormElementTextPhone element21 = FormElementTextPhone.createInstance().setTitle("Phone(parent)").setValue("+91");
        FormElementTextSingleLine element22 = FormElementTextSingleLine.createInstance().setTitle("Location").setHint("Enter Location");
        FormElementTextMultiLine element23 = FormElementTextMultiLine.createInstance().setTitle("Address");
        FormElementTextNumber element24 = FormElementTextNumber.createInstance().setTitle("Zip Code");

        FormHeader header3 = FormHeader.createInstance("Schedule");
        FormElementPickerDate element31 = FormElementPickerDate.createInstance().setTitle("From").setDateFormat("MMM dd, yyyy");
        FormElementPickerDate element32 = FormElementPickerDate.createInstance().setTitle("To").setDateFormat("MMM dd, yyyy");


        List<BaseFormElement> formItems = new ArrayList<>();
        formItems.add(header1);
        formItems.add(element11);
        formItems.add(element12);
        formItems.add(element13);
        formItems.add(header2);
        formItems.add(element20);
        formItems.add(element21);
        formItems.add(element22);
        formItems.add(element23);
        formItems.add(element24);
        formItems.add(header3);
        formItems.add(element31);
        formItems.add(element32);

        mFormBuilder.addFormElements(formItems);

    }


    @Override
    public void onValueChanged(BaseFormElement FormElement) {
    }
    public void submit(View view){
        Intent i= new Intent(this,Main2Activity.class);
        startActivity(i);
    }

}


