package com.t3h.Millionaire.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.t3h.Millionaire.Data.ListViewItem;
import com.t3h.Millionaire.R;

import java.util.ArrayList;

/**
 * Created by Wrongway on 13/01/2016.
 */
public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> arrayItem = new ArrayList<ListViewItem>();
    private ArrayList<Integer> arrayMoneyLandmark = new ArrayList<Integer>();
    private LayoutInflater inflater;

    public ListViewAdapter(Context context){
        inflater = LayoutInflater.from(context);
        initData();
    }

    private void initData() {
        arrayItem.add(new ListViewItem("15","150.000.000"));
        arrayItem.add(new ListViewItem("14","85.000.000"));
        arrayItem.add(new ListViewItem("13","60.000.000"));
        arrayItem.add(new ListViewItem("12","40.000.000"));
        arrayItem.add(new ListViewItem("11","30.000.000"));
        arrayItem.add(new ListViewItem("10","22.000.000"));
        arrayItem.add(new ListViewItem("09","14.000.000"));
        arrayItem.add(new ListViewItem("08","10.000.000"));
        arrayItem.add(new ListViewItem("07","6.000.000"));
        arrayItem.add(new ListViewItem("06","3.000.000"));
        arrayItem.add(new ListViewItem("05","2.000.000"));
        arrayItem.add(new ListViewItem("04","1.000.000"));
        arrayItem.add(new ListViewItem("03","600.000"));
        arrayItem.add(new ListViewItem("02","400.000"));
        arrayItem.add(new ListViewItem("01","200.000"));

        arrayMoneyLandmark.add(150000000);
        arrayMoneyLandmark.add(85000000);
        arrayMoneyLandmark.add(60000000);
        arrayMoneyLandmark.add(40000000);
        arrayMoneyLandmark.add(30000000);
        arrayMoneyLandmark.add(22000000);
        arrayMoneyLandmark.add(14000000);
        arrayMoneyLandmark.add(10000000);
        arrayMoneyLandmark.add(6000000);
        arrayMoneyLandmark.add(3000000);
        arrayMoneyLandmark.add(2000000);
        arrayMoneyLandmark.add(1000000);
        arrayMoneyLandmark.add(600000);
        arrayMoneyLandmark.add(400000);
        arrayMoneyLandmark.add(200000);
    }

    public ArrayList<ListViewItem> getArrs(){
        return arrayItem;
    }

    public ArrayList<Integer> getArrayMoneyLandmark() {
        return arrayMoneyLandmark;
    }

    @Override
    public int getCount() {
        return arrayItem.size();
    }

    @Override
    public ListViewItem getItem(int position) {
        return arrayItem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.listviewitem,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tv_number_landmark = (TextView)convertView.findViewById(R.id.tv_number_landmark);
            viewHolder.tv_money_landmark = (TextView) convertView.findViewById(R.id.tv_money_landmark);
        }

        viewHolder.tv_number_landmark.setText(arrayItem.get(position).getNumberQuestion());
        viewHolder.tv_money_landmark.setText(arrayItem.get(position).getMoneyLandmark());
        if (arrayItem.get(position).getNumberQuestion()=="05"||
                arrayItem.get(position).getNumberQuestion()=="10"||
                arrayItem.get(position).getNumberQuestion()=="15"){
            viewHolder.tv_number_landmark.setTextColor(Color.BLUE);
            viewHolder.tv_money_landmark.setTextColor(Color.BLUE);
        }

        return convertView;
    }

    private class ViewHolder{
        TextView tv_number_landmark;
        TextView tv_money_landmark;
    }
}
