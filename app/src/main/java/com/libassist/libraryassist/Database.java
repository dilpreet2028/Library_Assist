package com.libassist.libraryassist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Database {
    DatabaseHp dbhelp;
    Context con;
    String pattern="dd-MM-yyyy";

    public Database(Context context){
        dbhelp=new DatabaseHp(context);
        con=context;
    }

    public String today(){
        String date=new SimpleDateFormat(pattern).format(new Date());
        return date;
    }
    public String after(String now){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        int returndate=10;
        Calendar c=Calendar.getInstance();
        try {
            c.setTime(sdf.parse(now));
            c.add(Calendar.DATE,returndate );
            sdf=new SimpleDateFormat(pattern);
            Date newdate=new Date(c.getTimeInMillis());
            return sdf.format(newdate);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    public long add(String name) {
        long id;
        String doi,dor;
        SQLiteDatabase sqLiteDatabase=dbhelp.getWritableDatabase();
        ContentValues cn= new ContentValues();
        doi=today();
        dor=after(doi);
        cn.put(dbhelp.BNAME,name);
        cn.put(dbhelp.DOI,doi);
        cn.put(dbhelp.DOR,dor);
        id=sqLiteDatabase.insert(dbhelp.TBNAME,null,cn);
        return id;
    }
    public long check(String dor){
        //run with services.
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        Date start=null,end=null;
        String present= today();

        long days=0;
        try{
            start=sdf.parse(present);
            end=sdf.parse(dor);
            long diff = end.getTime() - start.getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            days = hours / 24;
        }
        catch(Exception e){
            Toast.makeText(con, "Error", Toast.LENGTH_SHORT).show();
        }
        return days;
    }
    public void display(){
        String[] colname={dbhelp.BNAME,dbhelp.DOI,dbhelp.DOR};
        ArrayList<String> ar=new ArrayList<>();
        String name,dor,doi;
        SQLiteDatabase sqLiteDatabase=dbhelp.getWritableDatabase();

        Cursor cursor=sqLiteDatabase.query(dbhelp.TBNAME,colname,null,null,null,null,null);
        while(cursor.moveToNext()){
            name=cursor.getString(cursor.getColumnIndex(dbhelp.BNAME));
            doi=cursor.getString(cursor.getColumnIndex(dbhelp.DOI));
            dor=cursor.getString(cursor.getColumnIndex(dbhelp.DOR));
            ar.add(name+" "+doi+" "+dor+"\n");//need to pass only the book name
            //rest for displaying three function will send the book ,issue date,retrun date
        }
        String all;
        all=ar.toString();
        Toast.makeText(con,all,Toast.LENGTH_LONG).show();
        Toast.makeText(con,all,Toast.LENGTH_LONG).show();
    }
    public void diff(){
        //to be run with services
        String[] col={dbhelp.BNAME,dbhelp.DOI,dbhelp.DOR};
        String doi,dor;
        ArrayList<String> ar=new ArrayList<>();
        String name;
        int count=0;
        SQLiteDatabase sql=dbhelp.getWritableDatabase();
        Cursor cursor= sql.query(dbhelp.TBNAME,col,null,null,null,null,null);
        while(cursor.moveToNext()) {
            long diff = check(cursor.getString(cursor.getColumnIndex(dbhelp.DOR)));
            if (diff <= 1) {
                count++;
                ar.add(cursor.getString(cursor.getColumnIndex(dbhelp.BNAME)));
            }
        }
        /* if(count>0){
                1.notiifcation support
                2.send all the books which are in the array list to the list view
                    so that it can display the list
                    (notification will have the pending intent for the list view for the
                    books to be returned )
                }*/
        String st=ar.toString();
        Toast.makeText(con,st+" "+count,Toast.LENGTH_SHORT).show();
        /*if(count==0)
                return null;
             else
                return ar;*/

    }
    //for deleting
    public void posdel(int pos)//need the pos from the listview...
    {
        String[] col={dbhelp.BNAME,dbhelp.DOI,dbhelp.DOR};
        SQLiteDatabase sql= dbhelp.getWritableDatabase();
        Cursor cursor=sql.query(dbhelp.TBNAME,col,null,null,null,null,null);
        cursor.moveToPosition(pos);
        deletebook(cursor.getString(cursor.getColumnIndex(dbhelp.BNAME)));
    }

    public void deletebook(String book){
        SQLiteDatabase sqLiteDatabase=dbhelp.getWritableDatabase();
        String[] args={book};
        sqLiteDatabase.delete(dbhelp.TBNAME,dbhelp.BNAME+"=?",args);
    }
    ////FOR DISPLAYING BOOK NAME,DOI,DOR IN THE INTENT WHICH IS USED BY THE LISTACTIVTY
    Cursor senddata;
    public void cursordis(int pos){
        //linked to the listonclicklistener
        String[] col={dbhelp.BNAME,dbhelp.DOI,dbhelp.DOR};
        SQLiteDatabase sql= dbhelp.getWritableDatabase();
        senddata=sql.query(dbhelp.TBNAME,col,null,null,null,null,null);
        senddata.moveToPosition(pos);
    }
    //LINKED TO THE INTENT TO DISPLAY THE CLICKED ONE
    public String sendbook(){
        return senddata.getString(senddata.getColumnIndex(dbhelp.BNAME));
    }
    public String senddoi(){
        return senddata.getString(senddata.getColumnIndex(dbhelp.DOR));
    }
    public String senddor(){
        return senddata.getString(senddata.getColumnIndex(dbhelp.DOI));
    }


    static public class DatabaseHp extends SQLiteOpenHelper {
        private static final String TBNAME="BOOKS";
        private static final String BNAME="bname";
        private static final String DOI="doi";
        private static final String DOR="dor";
        private static final int DBVER=2;
        private static final String CREATE_TB="Create table "+TBNAME+"("+BNAME+" varchar(256),"+DOI+" varchar(256),"+DOR+" varchar(256));";
        private static final String DROP_TB="Drop table if exists "+TBNAME;
        private  Context con;

        public DatabaseHp(Context context) {
            super(context, TBNAME, null,DBVER );
            con=context;
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TB);
                Toast.makeText(con,"Created",Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(con,"Failed",Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TB);
                Toast.makeText(con,"Updated",Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(con,"Failed Update",Toast.LENGTH_SHORT).show();
            }
        }


    }


}
