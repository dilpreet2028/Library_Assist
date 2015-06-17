package com.libassist.libraryassist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class thisbook extends ActionBarActivity {
    TextView book,doi,dor;//bname idae rdate
    Button breiss,bret;//return n rissue
    Database data;
    String b,di,dr;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thisbook);
        breiss=(Button)findViewById(R.id.butreissue);
        bret=(Button)findViewById(R.id.butreturn);
        book=(TextView)findViewById(R.id.bname);
        doi=(TextView)findViewById(R.id.idate);
        dor=(TextView)findViewById(R.id.rdate);
        data=new Database(this);
        breiss.setVisibility(View.VISIBLE);
        bret.setVisibility(View.VISIBLE);
        flag=getIntent().getIntExtra("flag",0);
        check(flag);
        b=getIntent().getStringExtra("book");
        di=getIntent().getStringExtra("doi");
        dr=getIntent().getStringExtra("dor");
            book.setText("Book Name: "+b);
            doi.setText("Issue Date: "+di);
            dor.setText("Return Date: "+dr);




    }
    private void check (int f){
        if(f==1)
            bret.setVisibility(View.INVISIBLE);
        else if(f==2)
            breiss.setVisibility(View.INVISIBLE);
    }
    public void reiss(View v){

        data.Rcheck(b);
        data.add(b);
        Toast.makeText(this,"Book Record Updated",Toast.LENGTH_SHORT).show();
        callmain();


    }
    public void ret(View v){
        data.Rcheck(b);
        callmain();
        Toast.makeText(this,"Book Record Deleted",Toast.LENGTH_SHORT).show();

    }
    public void callmain(){
        Intent in=new Intent(this,MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);

    }

}
