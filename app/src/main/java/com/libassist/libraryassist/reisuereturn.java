package com.libassist.libraryassist;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class reisuereturn extends ActionBarActivity {

Database d;
    ListView list;
     //   String[] bname={"xyz","syfs","yfs"};
     //  String[] returndate={"123","234","345"};


    Bundle bundle=getIntent().getExtras();
    String flag1 = bundle.getString("flag");//flag1 has value to show which button is clickes at homepage
    //use flag1 during onitemclicklistner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reisuereturn);

        list=(ListView)findViewById(R.id.listView);

        //for displaying bookname and its retun or issue date
       // ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.singlerow,R.id.textView,bname);
      //  ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,R.layout.singlerow,R.id.textView2,returndate);

      //  list.setAdapter(adapter);
      // list.setAdapter(adapter1);


            class singlerow  //to display them!take input from database
            {
                String bname;
                String idate;
               String rdate;
            }
        //making base adapter


        class customadapter extends BaseAdapter{
            ArrayList<singlerow> list;
            customadapter(){
                list=new ArrayList<singlerow>();

                /* * **************take data here from database to display in list******* */
            }

            @Override
             public int getCount() {
                return list.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }



            //ye 2 cheeze dekh dilpreet
            @Override   //to populate data*******isko google kariyo oye*****
            public long getItemId(int position) {
                return 0;
            }

            @Override   //  to take data from list and put it inside list*****
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }
        }

      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

          }
      });


   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reisuereturn, menu);
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

        return super.onOptionsItemSelected(item);*/
    }
}
