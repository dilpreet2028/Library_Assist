package com.libassist.libraryassist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class booklist extends ActionBarActivity {
    ListView list;
    ArrayList<String> array;
    Database data;
    int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);
        data=new Database(this);
        array=new ArrayList<>();
        list=(ListView)findViewById(R.id.listView);
        int notfiy=getIntent().getIntExtra("notify", 1);
        //for displaying bookname
        if(notfiy==1)
            array=data.display();
        else {
            try {
                array = data.givetolist();
            } catch (Exception e) {
                array=data.display();
            }
        }
        counter=getIntent().getIntExtra("option",0);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.singlerow,R.id.textView,array);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                            Intent in=new Intent(booklist.this,thisbook.class);
                                            data.cursordis(position);
                                            in.putExtra("book",data.sendbook());
                                            in.putExtra("doi",data.senddoi());
                                            in.putExtra("dor",data.senddor());
                                            in.putExtra("flag",counter);
                                            //pass any value here
                                            startActivity(in);



                                        }
                                    });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_booklist, menu);
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
