package ado.rigby.com.do_hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import ado.rigby.com.do_hackathon.Classes.CentOS;
import ado.rigby.com.do_hackathon.Classes.CoreOS;
import ado.rigby.com.do_hackathon.Classes.Debian;
import ado.rigby.com.do_hackathon.Classes.Fedora;
import ado.rigby.com.do_hackathon.Classes.FreeBSD;
import ado.rigby.com.do_hackathon.Classes.Ubuntu;

public class Select extends AppCompatActivity {
    ListView lv;
    static String slugs;
    ArrayList<String> ob=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        lv= (ListView) findViewById(R.id.listView2);

        switch(new Bundle().get("key").toString())
        {
            case "Ubuntu":final ArrayList<Ubuntu> ub=CreateDroplet.ub;
                for(int x=0;x<ub.size();x++)
                    ob.add(ub.get(x).getName());
                ArrayAdapter ad=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ob);
                lv.setAdapter(ad);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CreateDroplet.fs.setSlug(slugs);
                    }
                });
                break;
            case "CentOS":final ArrayList<CentOS> co=CreateDroplet.co;
                for(int x=0;x<co.size();x++)
                    ob.add(co.get(x).getName());
                ArrayAdapter ad2=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ob);
                lv.setAdapter(ad2);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CreateDroplet.fs.setSlug(slugs);
                    }
                });
                break;
            case "CoreOS":final ArrayList<CoreOS> cor=CreateDroplet.cor;
                for(int x=0;x<cor.size();x++)
                    ob.add(cor.get(x).getName());
                ArrayAdapter ad3=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ob);
                lv.setAdapter(ad3);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CreateDroplet.fs.setSlug(slugs);;
                    }
                });
                break;
            case "Fedora":final ArrayList<Fedora> fd=CreateDroplet.fd;
                for(int x=0;x<fd.size();x++)
                    ob.add(fd.get(x).getName());
                ArrayAdapter ad4=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ob);
                lv.setAdapter(ad4);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CreateDroplet.fs.setSlug(slugs);
                    }
                });
                break;
            case "FreeBSD":final ArrayList<FreeBSD> fb=CreateDroplet.fb;
                for(int x=0;x<fb.size();x++)
                    ob.add(fb.get(x).getName());
                ArrayAdapter ad5=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ob);
                lv.setAdapter(ad5);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CreateDroplet.fs.setSlug(slugs);
                    }
                });
                break;
            case "Debian":final ArrayList<Debian> deb=CreateDroplet.deb;
                for(int x=0;x<deb.size();x++)
                    ob.add(deb.get(x).getName());
                ArrayAdapter ad6=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,ob);
                lv.setAdapter(ad6);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        CreateDroplet.fs.setSlug(slugs);
                    }
                });
                break;

        }

        Intent intent=new Intent(Select.this,SizeSel.class);
        startActivity(intent);




    }
}
