package ado.rigby.com.do_hackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class End extends AppCompatActivity {
    String url="https://api.digitalocean.com/v2/droplets";
    EditText et1;
    Button b2;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end2);
        requestQueue= Volley.newRequestQueue(this);

        et1= (EditText) findViewById(R.id.editText2);
        b2= (Button) findViewById(R.id.button5);

        final JSONObject obj = new JSONObject();
        try{
            obj.put("name",et1.getText().toString());
            obj.put("region",Main2Activity.fs.getRegion());
            obj.put("size",Main2Activity.fs.getSize());
            obj.put("image",Main2Activity.fs.getSlug());
            obj.put("ssh_keys",null);
            obj.put("backups",Main2Activity.fs.isBckup());
            obj.put("ipv6",Main2Activity.fs.isIpv());
            obj.put("user_data",null);
            obj.put("private_networking",Main2Activity.fs.isPvtnw());
            obj.put("volumes",null);

        }catch (Exception e)
        {

        }

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
