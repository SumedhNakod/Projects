package com.example.adroit.leaveapplication;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.adroit.leaveapplication.listener.FormItemEditTextListener;
import com.example.adroit.leaveapplication.listener.OnFormElementValueChangedListener;
import com.example.adroit.leaveapplication.listener.ReloadListener;
import com.example.adroit.leaveapplication.model.BaseFormElement;
import com.example.adroit.leaveapplication.viewholder.BaseViewHolder;
import com.example.adroit.leaveapplication.viewholder.FormElementHeader;
import com.example.adroit.leaveapplication.viewholder.FormElementPickerDateViewHolder;
import com.example.adroit.leaveapplication.viewholder.FormElementTextEmailViewHolder;
import com.example.adroit.leaveapplication.viewholder.FormElementTextMultiLineViewHolder;
import com.example.adroit.leaveapplication.viewholder.FormElementTextNumberViewHolder;
import com.example.adroit.leaveapplication.viewholder.FormElementTextPhoneViewHolder;
import com.example.adroit.leaveapplication.viewholder.FormElementTextSingleLineViewHolder;

import java.util.ArrayList;
import java.util.List;



/**
 * The adapter the holds and displays the form objects
 */

public class FormAdapter extends RecyclerView.Adapter<BaseViewHolder> implements ReloadListener {

    private Context mContext;
    private List<BaseFormElement> mDataset;
    private OnFormElementValueChangedListener mListener;
    private LinearLayout parentLayout;
    private ArrayList<String> medittxts= new ArrayList<>();

    public FormAdapter(Context mContext, OnFormElementValueChangedListener mListener) {
        this.mContext = mContext;
        this.mDataset = mDataset;
        this.mListener = mListener;
        this.parentLayout = parentLayout;
        this.medittxts = medittxts;
    }

    /**
     * public constructor with context
     * @param context
     */


    /**
     * adds list of elements to be shown
     * @param formObjects
     */
    public void addElements(List<BaseFormElement> formObjects) {
        this.mDataset = formObjects;
        notifyDataSetChanged();
    }

    /**
     * adds single element to be shown
     * @param formObject
     */
    public void addElement(BaseFormElement formObject) {
        this.mDataset.add(formObject);
        notifyDataSetChanged();
    }

    /**
     * set value for any unique index
     * @param position
     * @param value
     */
    public void setValueAtIndex(int position, String value) {
        BaseFormElement baseFormElement = mDataset.get(position);
        baseFormElement.setValue(value);
        notifyDataSetChanged();
    }

    /**
     * set value for any unique tag
     * @param tag
     * @param value
     */
    public void setValueAtTag(int tag, String value) {
        for (BaseFormElement f : this.mDataset) {
            if (f.getTag() == tag) {
                f.setValue(value);
                return;
            }
        }
        notifyDataSetChanged();
    }

    /**
     * get value of any element by tag
     * @param index
     * @return
     */
    public BaseFormElement getValueAtIndex(int index) {
        return (mDataset.get(index));
    }

    /**
     * get value of any element by tag
     * @param tag
     * @return
     */
    public BaseFormElement getValueAtTag(int tag) {
        for (BaseFormElement f : this.mDataset) {
            if (f.getTag() == tag) {
                return f;
            }
        }

        return null;
    }

    /**
     * get whole dataset
     * @return
     */
    public List<BaseFormElement> getDataset() {
        return mDataset;
    }

    /**
     * get value changed listener
     * @return
     */
    public OnFormElementValueChangedListener getValueChangeListener() {
        return mListener;
    }

    /**
     * gets total item count
     * @return
     */
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    /**
     * gets view item type based on header, or the form element type
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mDataset.get(position).getType();
    }


    /**
     * creating the view holder to be shown for a position
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // get layout based on header or element type

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v;
        switch (viewType) {
            case BaseFormElement.TYPE_HEADER:
                v = inflater.inflate(R.layout.form_element_header, parent, false);
                return new FormElementHeader(v);
            case BaseFormElement.TYPE_EDITTEXT_TEXT_SINGLELINE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextSingleLineViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_TEXT_MULTILINE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextMultiLineViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_NUMBER:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextNumberViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_EMAIL:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextEmailViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_EDITTEXT_PHONE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextPhoneViewHolder(v, new FormItemEditTextListener(this));
            case BaseFormElement.TYPE_PICKER_DATE:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementPickerDateViewHolder(v, mContext, this);




            default:
                v = inflater.inflate(R.layout.form_element, parent, false);
                return new FormElementTextSingleLineViewHolder(v, new FormItemEditTextListener(this));
        }
    }

    /**
     * draws the view for the position specific view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {


        // updates edit text listener index
        if (holder.getListener() != null) {
            holder.getListener().updatePosition(holder.getAdapterPosition());
        }

        // gets current object
        BaseFormElement currentObject = mDataset.get(position);
        holder.bind(position, currentObject, mContext);



    }

    /**
     * use the listener to update value and notify dataset changes to adapter
     * @param position
     * @param updatedValue
     */
    @Override
    public void updateValue(int position, String updatedValue) {
        mDataset.get(position).setValue(updatedValue);
        notifyDataSetChanged();
        if (mListener != null)
            mListener.onValueChanged(mDataset.get(position));
    }

}