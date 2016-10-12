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

public class DateAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] dateStrings , storeStrings;

    public DateAdapter(Context context, String[] dateStrings, String[] storeStrings) {
        this.context = context;
        this.dateStrings = dateStrings;
        this.storeStrings = storeStrings;
    }

    @Override
    public int getCount() {
        return dateStrings.length;
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

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.date_listview, parent , false);

        //Bind Widget
        TextView dateTextView = (TextView) view1.findViewById(R.id.textView6);
        TextView storeTextView = (TextView) view1.findViewById(R.id.textView7);

        //Show View
        dateTextView.setText("Date = " + dateStrings[position]);
        storeTextView.setText("Store = " + storeStrings[position]);


        return view1;
    }
} // Main Class
