package com.example.safa.activity_intent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="userdetails.db";
    private static final String TABLE_USER_NAME="User_details";
    private static final String USERNAME="Username";
    private static final String NAME="name";
    private static final String Father_NAME="fathername";
    private static final String Password="Password";
    private static final String ID="Id";
    private static final String Phonenumber="phonenumber";
    private static final int  Database_VERSION=13;

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER_NAME+ "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " VARCHAR(255) NOT NULL,"+ Father_NAME + " VARCHAR(255) NOT NULL," + Phonenumber + " INTEGER NOT NULL,"
            + USERNAME + " VARCHAR(255) NOT NULL UNIQUE," + Password + " VARCHAR(255) NOT NULL" + ")";




    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER_NAME;

    private Context context;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, Database_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try
        {
            sqLiteDatabase.execSQL(CREATE_USER_TABLE);
            Toast.makeText(context,"onCreate method is callled ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try
        {
            Toast.makeText(context,"onUpgrade method is callled ",Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL(DROP_USER_TABLE);
            onCreate(sqLiteDatabase);
            Toast.makeText(context,"onUpgrade method is callled ",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();;
        }

    }

    public long insertData(UserDetails userDetails)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,userDetails.getName());
        contentValues.put(Father_NAME,userDetails.getFatherName());
        contentValues.put(Phonenumber,userDetails.getPhoneNumber());
        contentValues.put(USERNAME,userDetails.getUsername());
        contentValues.put(Password,userDetails.getPassword());

        long rowID= sqLiteDatabase.insert(TABLE_USER_NAME,null,contentValues);
        return rowID;
    }




    public Boolean findpassword( String uname, String password)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_USER_NAME,null);

        Boolean result =false;

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"No username is available ",Toast.LENGTH_SHORT).show();
        }

        else
        {
            while ((cursor.moveToNext()))
            {
                String username=cursor.getString(4);
                String pass=cursor.getString(5);
                if(username.equals(uname) && pass.equals(password))
                {
                    result =true;
                    break;
                }

            }

        }
        return  result;

    }

    public Boolean findfathername( String uname, String father)
    {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_USER_NAME,null);

        Boolean result =false;

        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"No username is available ",Toast.LENGTH_SHORT).show();
        }

        else
        {
            while ((cursor.moveToNext()))
            {
                String username=cursor.getString(4);
                String fathername=cursor.getString(2);
                if(username.equals(uname) && fathername.equals(father))
                {
                    result =true;
                    break;
                }

            }

        }
        return  result;

    }


    public boolean newPassword(String Username,String newPassword)
    {

        String idd;
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_USER_NAME,null);
  /*      while ((cursor.moveToNext()))
        {
            String username=cursor.getString(4);

            if(username.equals(Username) )
            {
                idd = cursor.getString(0);
                //result =true;
                break;
            }

        }*/

        ContentValues contentValues=new ContentValues();
        contentValues.put(Password,newPassword);




        sqLiteDatabase.update(TABLE_USER_NAME,contentValues,USERNAME+" = ?",new String[]{Username});
        return  true;


    }






  /*  public boolean updatemethod(String id, String name,String age)
    {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(ID,id);

        sqLiteDatabase.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});
        return  true;


    }*/



}
