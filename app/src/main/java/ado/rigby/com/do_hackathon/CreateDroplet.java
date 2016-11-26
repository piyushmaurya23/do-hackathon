package ado.rigby.com.do_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import ado.rigby.com.do_hackathon.Classes.CentOS;
import ado.rigby.com.do_hackathon.Classes.CoreOS;
import ado.rigby.com.do_hackathon.Classes.Debian;
import ado.rigby.com.do_hackathon.Classes.Fedora;
import ado.rigby.com.do_hackathon.Classes.FreeBSD;
import ado.rigby.com.do_hackathon.Classes.FullSelection;
import ado.rigby.com.do_hackathon.Classes.Ubuntu;

public class CreateDroplet extends AppCompatActivity {

    String url="https://api.digitalocean.com/v2/images?page=1&per_page=28&type=distribution";
    RequestQueue requestQueue;
    static ArrayList<Ubuntu> ub=new ArrayList<>();
    static ArrayList<CentOS> co=new ArrayList<>();
    static ArrayList<CoreOS> cor=new ArrayList<>();
    static ArrayList<Debian> deb=new ArrayList<>();
    static ArrayList<Fedora> fd=new ArrayList<>();
    static ArrayList<FreeBSD> fb=new ArrayList<>();
    ListView lv;
    String slug;
    static FullSelection fs=new FullSelection();
    String ar[]={"Ubuntu","CentOS","CoreOS","Debian","Fedora","FreeBSD"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_droplet);
        lv= (ListView) findViewById(R.id.listView);

        requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray jr=jsonObject.getJSONArray("images");
                    for(int x=0;x<jr.length();x++)
                    {
                        JSONObject js=jr.getJSONObject(x);
                        switch(js.getString("distribution"))
                        {
                            case "Ubuntu": Ubuntu ubuntu=new Ubuntu();
                                ubuntu.setDistribution(js.getString("distribution"));
                                ubuntu.setName(js.getString("name"));
                                ubuntu.setSlug(js.getString("slug"));
                                ub.add(ubuntu);
                                break;
                            case "Fedora":Fedora fed=new Fedora();
                                fed.setDistribution(js.getString("distribution"));
                                fed.setName(js.getString("name"));
                                fed.setSlug(js.getString("slug"));
                                fd.add(fed);
                                break;
                            case "FreeBSD":FreeBSD fbs=new FreeBSD();
                                fbs.setDistribution(js.getString("distribution"));
                                fbs.setName(js.getString("name"));
                                fbs.setSlug(js.getString("slug"));
                                fb.add(fbs);
                                break;
                            case "CentOS":CentOS cos=new CentOS();
                                cos.setDistribution(js.getString("distribution"));
                                cos.setName(js.getString("name"));
                                cos.setSlug(js.getString("slug"));
                                co.add(cos);
                                break;
                            case "Debian":Debian db=new Debian();
                                db.setDistribution(js.getString("distribution"));
                                db.setName(js.getString("name"));
                                db.setSlug(js.getString("slug"));
                                deb.add(db);
                                break;
                            case "CoreOS":CoreOS coreOS=new CoreOS();
                                coreOS.setDistribution(js.getString("distribution"));
                                coreOS.setSlug(js.getString("slug"));
                                coreOS.setName(js.getString("name"));
                                cor.add(coreOS);
                                break;
                        }


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> mp=new HashMap<String,String>();

                return super.getParams();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> mp=new HashMap<>();
                mp.put("Authorization","Bearer "+MainActivity.token);
                return mp;
            }
        };
        requestQueue.add(jsonObjectRequest);
        ArrayAdapter ad=new ArrayAdapter(this,android.R.layout.activity_list_item,ar);
        lv.setAdapter(ad);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                switch (position) {
                    case 0:
                        Intent i = new Intent(CreateDroplet.this, Select.class);
                        i.putExtra("key", "Ubuntu");
                        startActivity(i);

                        break;
                    case 1:
                        Intent i2 = new Intent(CreateDroplet.this, Select.class);
                        i2.putExtra("key", "CentOS");
                        startActivity(i2);
                        break;
                    case 2:
                        Intent i3 = new Intent(CreateDroplet.this, Select.class);
                        i3.putExtra("key", "CoreOS");
                        startActivity(i3);
                        break;
                    case 3:
                        Intent i4 = new Intent(CreateDroplet.this, Select.class);
                        i4.putExtra("key", "Debian");
                        startActivity(i4);
                        break;
                    case 4:
                        Intent i5 = new Intent(CreateDroplet.this, Select.class);
                        i5.putExtra("key", "Fedora");
                        startActivity(i5);
                        break;
                    case 5:
                        Intent i6 = new Intent(CreateDroplet.this, Select.class);
                        i6.putExtra("key", "FreeBSD");
                        startActivity(i6);
                        break;

                }
            }
        });


    }
}