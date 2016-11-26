package ado.rigby.com.do_hackathon;

import android.view.View;
import android.widget.TextView;

/**
 * Created by soumya singh on 11/26/2016.
 */
public class ViewHolder {
    static TextView text,address,memory,disk;



    View convertView;
    public ViewHolder(View convertView) {
        text = (TextView) convertView.findViewById(R.id.droplet_name);
        address=(TextView)convertView.findViewById(R.id.ip_address);
        memory=(TextView)convertView.findViewById(R.id.memory);
        disk=(TextView) convertView.findViewById(R.id.disk);

    }
}
