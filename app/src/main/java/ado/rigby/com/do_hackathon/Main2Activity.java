package ado.rigby.com.do_hackathon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

import ado.rigby.com.do_hackathon.Classes.CentOS;
import ado.rigby.com.do_hackathon.Classes.CoreOS;
import ado.rigby.com.do_hackathon.Classes.Debian;
import ado.rigby.com.do_hackathon.Classes.Fedora;
import ado.rigby.com.do_hackathon.Classes.FreeBSD;
import ado.rigby.com.do_hackathon.Classes.FullSelection;
import ado.rigby.com.do_hackathon.Classes.Ubuntu;

public class Main2Activity extends AppCompatActivity {
    RequestQueue requestQueue;
    String url="https://api.digitalocean.com/v2/images?page=1&per_page=28&type=distribution";
    static ArrayList<Ubuntu> ub=new ArrayList<>();
    static ArrayList<CentOS> co=new ArrayList<>();
    static ArrayList<CoreOS> cor=new ArrayList<>();
    static ArrayList<Debian> deb=new ArrayList<>();
    static ArrayList<Fedora> fd=new ArrayList<>();
    static ArrayList<FreeBSD> fb=new ArrayList<>();
    ListView lv;
    Spinner name,dis;
    String []names;
    static FullSelection fs=new FullSelection();
    Button b,b2;
    String ar[]={"Ubuntu","CentOS","CoreOS","Debian","Fedora","FreeBSD"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name= (Spinner) findViewById(R.id.spinner);
        dis= (Spinner) findViewById(R.id.spinner);
        b2= (Button) findViewById(R.id.button3);

        requestQueue= Volley.newRequestQueue(this);
        lv= (ListView) findViewById(R.id.listView);
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Please Wait");
        pd.show();
        b= (Button) findViewById(R.id.button);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                pd.dismiss();
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> mp=new HashMap<>();
                mp.put("Authorization","Bearer "+MainActivity.token);
                return mp;
            }
        };
        requestQueue.add(jsonObjectRequest);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,ar);
        name.setAdapter(adapter);
        names=new String[ub.size()];
        for(int x=0;x<ub.size();x++)
            names[x]=ub.get(x).getName();
        ArrayAdapter ad2=new ArrayAdapter(this,android.R.layout.simple_spinner_item,names);
        dis.setAdapter(ad2);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(name.getSelectedItemPosition())
                {
                    case 0:names=new String[ub.size()];
                        for(int x=0;x<ub.size();x++)
                            names[x]=ub.get(x).getName();
                        break;
                    case 1:names=new String[co.size()];
                        for(int x=0;x<co.size();x++)
                            names[x]=co.get(x).getName();
                        break;
                    case 2:names=new String[cor.size()];
                        for(int x=0;x<cor.size();x++)
                            names[x]=cor.get(x).getName();
                        break;
                    case 3:names=new String[deb.size()];
                        for(int x=0;x<deb.size();x++)
                            names[x]=deb.get(x).getName();
                        break;
                    case 4:names=new String[fd.size()];
                        for(int x=0;x<fd.size();x++)
                            names[x]=fd.get(x).getName();
                        break;
                    case 5:names=new String[fb.size()];
                        for(int x=0;x<fb.size();x++)
                            names[x]=fb.get(x).getName();
                        break;
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int posn=name.getSelectedItemPosition();
                int posd=dis.getSelectedItemPosition();
                switch(posn)
                {
                    case 0:fs.setSlug(ub.get(posd).getSlug());
                        break;
                    case 1:fs.setSlug(co.get(posd).getSlug());
                        break;
                    case 2:fs.setSlug(cor.get(posd).getSlug());
                        break;
                    case 3:fs.setSlug(fd.get(posd).getSlug());
                        break;
                    case 4:fs.setSlug(fb.get(posd).getSlug());
                        break;
                    case 5:fs.setSlug(deb.get(posd).getSlug());
                        break;
                }

                Intent i =new Intent(Main2Activity.this,SizeSel2.class);
                startActivity(i);
            }
        });


    }
}