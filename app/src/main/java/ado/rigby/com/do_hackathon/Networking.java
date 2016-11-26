package ado.rigby.com.do_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.EditText;
import android.widget.ListView;

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
    ListView listView;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);
        listView= (ListView) findViewById(R.id.listView3);
        ed= (EditText) findViewById(R.id.editText);
        requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mp=new HashMap<>();

                    mp.put("name", DisplayDroplets.droplets_list.get(0));
                mp.put("")

                return super.getParams();
            }
        };

        requestQueue.add(jsonObjectRequest);





    }
}
