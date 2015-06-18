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
import android.widget.Toast;

import java.util.ArrayList;


public class booklist extends ActionBarActivity {
    ListView list;
    ArrayList<String> array;
    Database data;
    int counter,check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        check=10;
        setContentView(R.layout.activity_booklist);
        list=(ListView)findViewById(R.id.listView);
        data=new Database(this);
        array=new ArrayList<>();

            array = data.display();


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
                                            overridePendingTransition(R.anim.fadeout,R.anim.fadein);
                                            if(counter==1||counter==2){

                                            }
                                            else{
                                                finish();
                                            }




                                        }
                                    });




    }


}
