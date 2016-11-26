package ado.rigby.com.do_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
import java.util.List;
import java.util.Map;

import model.droplet;

public class DisplayDroplets extends AppCompatActivity {
    String url="https://api.digitalocean.com/v2/droplets";
    String token="478d10b07905b60e04e949aa9e35ab7bc19f1b4fbe4d398aaf16d51d7986da73";
    RequestQueue requestQueue;
    ListView list;
    CustomAdapter adapter;
    static List<droplet> droplet_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_droplets);
        list=(ListView) findViewById(R.id.list_droplet);
        adapter=new CustomAdapter(this,R.layout.list_item,droplet_list);

        requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest obj=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.v("response",jsonObject.toString());
               droplet_list.clear();
                try {
                    JSONArray arr=jsonObject.getJSONArray("droplets");
                    Log.v("array",arr.toString());
                    for(int i=0;i<arr.length();i++){
                        JSONObject jobj=arr.getJSONObject(i);
                        String name=""+jobj.getString("name");
                        String memory=" "+jobj.getString("memory");
                        String disk=" " +jobj.getString("disk");
                        JSONObject ip_obj=jobj.getJSONObject("networks");
                        JSONArray jarr=ip_obj.getJSONArray("v4");
                        JSONObject json_obj=jarr.getJSONObject(0);
                        String ip_adrress=json_obj.getString("ip_address");
                        Log.v("name",ip_adrress);
                        droplet bk=new droplet();
                        bk.setDroplet_name(name);
                        bk.setMemory(memory);
                        bk.setIp_address(ip_adrress);
                        bk.setDisk_space(disk);
                        droplet_list.add(bk);
                        adapter.notifyDataSetChanged();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                list.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.v("error",volleyError.getMessage());
            }
        }){


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> mp=new HashMap<String, String>();
                mp.put("Authorization","Bearer " + token);
                return mp;
            }

        };
        requestQueue.add(obj);
    }}






