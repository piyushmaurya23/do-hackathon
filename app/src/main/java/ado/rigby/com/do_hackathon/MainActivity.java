package ado.rigby.com.do_hackathon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText token_txt;
    Button token_btn;
    String url="https://api.digitalocean.com/v2/droplets";
    String token="478d10b07905b60e04e949aa9e35ab7bc19f1b4fbe4d398aaf16d51d7986da73";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        token_txt=(EditText)findViewById(R.id.token);
        token_btn=(Button)findViewById(R.id.token_btn);
        token_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              JsonObjectRequest obj=new JsonObjectRequest(Method.GET, url, null, new Response.Listener<JSONObject>() {
                  @Override
                  public void onResponse(JSONObject jsonObject) {
                    Log.v("response",jsonObject.toString());
                  }
              }, new Response.ErrorListener() {
                  @Override
                  public void onErrorResponse(VolleyError volleyError) {

                  }
              }){
                  @Override
                  public Map<String, String> getHeaders() throws AuthFailureError {
                      Map<String,String> mp=new HashMap<String, String>();
                      mp.put("Authorization","Bearer "+token);
                      return mp;
                  }


                  //                  @Override
//                  protected Map<String, String> getParams() throws AuthFailureError {
//                      Map<String,String> mp=new HashMap<String, String>();
//                      mp.put("client_id",)
//                      return mp;
//                  }
              };

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
