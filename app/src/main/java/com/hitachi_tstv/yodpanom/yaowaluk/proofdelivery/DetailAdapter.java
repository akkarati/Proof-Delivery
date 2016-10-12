package com.hitachi_tstv.yodpanom.yaowaluk.proofdelivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by akkarati on 2016-10-12.
 */

public class DetailAdapter extends BaseAdapter {

    //Explicit
    private Context context;
    private String[] workSheetStrings ,storenameStrings , planArrivalTimeStrings ;
    private TextView workSheetTextView , storeNameTextView , planArrivalTextView;

    public DetailAdapter(Context context, String[] workSheetStrings, String[] storenameStrings, String[] planArrivalTimeStrings) {
        this.context = context;
        this.workSheetStrings = workSheetStrings;
        this.storenameStrings = storenameStrings;
        this.planArrivalTimeStrings = planArrivalTimeStrings;
    }

    @Override
    public int getCount() {
        return workSheetStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService((context.LAYOUT_INFLATER_SERVICE));
        View view1 = layoutInflater.inflate(R.layout.detail_listview , parent , false);

        //Bind Widget
        workSheetTextView = (TextView) view1.findViewById(R.id.textView11);
        storeNameTextView = (TextView) view1.findViewById(R.id.textView12);
        planArrivalTextView = (TextView) view1.findViewById(R.id.textView13);

        //Show View
        workSheetTextView.setText(workSheetStrings[position]);
        storeNameTextView.setText(storenameStrings[position]);
        planArrivalTextView.setText(planArrivalTimeStrings[position]);



        return view1;
    }
} // Main Class
