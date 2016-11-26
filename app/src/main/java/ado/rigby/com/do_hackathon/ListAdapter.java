package ado.rigby.com.do_hackathon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Roopesh on 26-11-2016.
 */
public class ListAdapter extends ArrayAdapter {
    ArrayList<String> al;
    Context context;
    public ListAdapter(Context context, int resource, ArrayList<String> objects) {

        super(context,resource,objects);
        al=objects;
        this.context=context;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vv=LayoutInflater.from(context).inflate(R.layout.custom_list,parent);
        TextView tv1= (TextView) vv.findViewById(R.id.textView2);
        TextView tv2= (TextView) vv.findViewById(R.id.textView3);
        TextView tv3= (TextView) vv.findViewById(R.id.textView4);

        tv1.setText(al.get(position));
        //tv2.setText("Disk Storage:- " +SizeSel.disk.get(position)+"GB");
        tv3.setText("Price:- "+SizeSel.price.get(position)+"$");


        return vv;
    }
}
