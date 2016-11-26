package ado.rigby.com.do_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Networking extends AppCompatActivity {

    String url="https://api.digitalocean.com/v2/domains";
    EditText ed;
    Button b;
    ListView listView;
    RequestQueue requestQueue;
    Spinner sp;
    String []ar=new String[DisplayDroplets.droplet_list.size()];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);
        listView= (ListView) findViewById(R.id.listView3);
        for(int x=0;x<DisplayDroplets.droplet_list.size();x++)
            ar[x]=DisplayDroplets.droplet_list.get(x).getDroplet_name();


        b= (Button) findViewById(R.id.button);
        ed= (EditText) findViewById(R.id.editText);
        requestQueue= Volley.newRequestQueue(this);
        ArrayAdapter ad=new ArrayAdapter(this, android.R.layout.simple_spinner_item,ar);
        sp.setAdapter(ad);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ed.getText().toString()!=null) {
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(), new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject jsonObject) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> mp = new HashMap<>();
                            mp.put("name", ed.getText().toString());
                            mp.put("ip_address", DisplayDroplets.droplet_list.get(sp.getSelectedItemPosition()).getIp_address());
                            return mp;
                        }
                    };

                    requestQueue.add(jsonObjectRequest);
                }
                else
                    ed.setError("Please enter Domain here");
            }
        });
    }
}
