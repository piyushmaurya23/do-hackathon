package ado.rigby.com.do_hackathon;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import model.droplet;

/**
 * Created by soumya singh on 11/26/2016.
 */
public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private static List<droplet> dropletlist;
    public CustomAdapter(Context context, int resource, List<droplet> objects) {

        super(context, resource);
        this.context=context;
        this.dropletlist=objects;

    }

    @Override
    public int getCount() {
        return dropletlist.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.list_item,parent,false);


        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }

                // Log.v("statusofbook","its clicked");
                droplet bk2=dropletlist.get(position);
                String ttn=bk2.getDroplet_name();





        //Display book name in the textview widget

       // TextView tv=(TextView)view.findViewById(R.id.text);
        //  tv.setText(books.getTitle_name());
        holder.text.setText(bk2.getDroplet_name());
        holder.memory.setText(bk2.getMemory());
        holder.disk.setText(bk2.getDisk_space());
        holder.address.setText(bk2.getMemory());
        notifyDataSetChanged();
        return convertView;
    }
}
