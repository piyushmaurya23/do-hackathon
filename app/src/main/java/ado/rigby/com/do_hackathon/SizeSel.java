package ado.rigby.com.do_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SizeSel extends AppCompatActivity {
    ListView lv;
    RequestQueue requestQueue;
    String url="https://api.digitalocean.com/v2/sizes";
    static ArrayList<String> price=new ArrayList<>();
    static ArrayList<String> disk=new ArrayList<>();
    ArrayList<String> al=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_sel);

        lv= (ListView) findViewById(R.id.listView3);
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("sizes");
                    for(int x=0;x<6;x++)
                    {
                        JSONObject js=jsonArray.getJSONObject(x);
                        price.add(js.getString("price_monthly"));
                        disk.add(js.getString("disk"));
                        al.add(js.getString("slug"));
                        ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,al);
                        lv.setAdapter(ad);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> sp=new HashMap<>();
                sp.put("Authorization","Bearer "+MainActivity.token);
                return sp;
            }
        };
        requestQueue.add(jsonObjectRequest);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CreateDroplet.fs.setSize(al.get(position));
            }
        });


    }
}
