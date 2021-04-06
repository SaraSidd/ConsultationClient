package com.example.consultation.myapplication;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class ChatAdapter extends ArrayAdapter<ModelChat> {
    int DocID;
    public ChatAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ChatAdapter(Context context, int resource, List<ModelChat> items) {
        super(context, resource, items);
        this.DocID = DocID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.chat_item, null);
        }

        ModelChat p = getItem(position);
        //String p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.tv_thread);

            if (tt1 != null) {
                if(p.getSendBy().equals("Doctor"))
                    tt1.setGravity(Gravity.END);
                tt1.setText(p.getMesseges());
            }


        }

        return v;
    }
}
