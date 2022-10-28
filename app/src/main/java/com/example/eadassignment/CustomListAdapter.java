package com.example.eadassignment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends BaseAdapter {

    private ArrayList<GetStationList> getStationLists;
    private Context context;
    private int layout;

    public CustomListAdapter(ArrayList<GetStationList> getStationLists, Context context, int layout) {
        this.getStationLists = getStationLists;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return getStationLists.size();
    }

    @Override
    public Object getItem(int i) {
        return getStationLists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{
        TextView txt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder();
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout,null);

        viewHolder.txt = view.findViewById(R.id.listTxt);

        GetStationList getStationList= getStationLists.get(i);
        viewHolder.txt.setText(getStationList.getName());
        return view;
    }
}
