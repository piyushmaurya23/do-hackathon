package ado.rigby.com.do_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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

public class SizeSel2 extends AppCompatActivity {

    String regionUrl="https://api.digitalocean.com/v2/regions";
    RequestQueue requestQueue;
    Spinner size,region;
    String sizeUrl="https://api.digitalocean.com/v2/sizes";
    static ArrayList<String> price=new ArrayList<>();
    static ArrayList<String> slug=new ArrayList<>();
    static ArrayList<String> regionName=new ArrayList<>();
    static ArrayList<String> regionSlug=new ArrayList<>();
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size_sel2);
        size= (Spinner) findViewById(R.id.size);
        b= (Button) findViewById(R.id.button4);
        region= (Spinner) findViewById(R.id.region);
        requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,sizeUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("sizes");
                    for(int x=0;x<6;x++)
                    {
                        JSONObject js=jsonArray.getJSONObject(x);
                        price.add(js.getString("price_monthly"));

                        slug.add(js.getString("slug"));
                        ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,slug);
                        size.setAdapter(ad);
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

        JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, regionUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jsonArray=jsonObject.getJSONArray("regions");
                    for(int x=0;x<jsonArray.length();x++)
                    {
                        JSONObject js=jsonArray.getJSONObject(x);
                        regionName.add(js.getString("name"));
                        regionSlug.add(js.getString("slug"));
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
                Map<String,String> mp=new HashMap<>();
                mp.put("Authorization","Bearer "+MainActivity.token);
                return mp;
            }
        };

        requestQueue.add(jsonObjectRequest1);
        ArrayAdapter ad2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,slug);
        size.setAdapter(ad2);
        ArrayAdapter ad1=new ArrayAdapter(this,android.R.layout.simple_spinner_item,regionName);
        region.setAdapter(ad1);
        final CheckBox pvtnw= (CheckBox) findViewById(R.id.checkBox);
        final CheckBox backup=(CheckBox)findViewById(R.id.checkBox3);
        final CheckBox ipv= (CheckBox) findViewById(R.id.checkBox2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main2Activity.fs.setSize(slug.get(size.getSelectedItemPosition()));
                Main2Activity.fs.setRegion(regionSlug.get(region.getSelectedItemPosition()));
                Main2Activity.fs.setBckup(backup.isChecked());
                Main2Activity.fs.setPvtnw(pvtnw.isChecked());
                Main2Activity.fs.setIpv(ipv.isChecked());

            }
        });

    }
}
