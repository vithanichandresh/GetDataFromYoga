package com.example.krima.getdatafromyoga;

import android.content.ContentValues;
import android.content.Context;
import android.util.Base64;
import android.util.Log;

import android.database.Cursor;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/*

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteOpenHelper;

*/

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by KRIMA on 31-07-2017.
 */

public class SQliteFavoriteHelper extends SQLiteOpenHelper {

    private static final String DB_PATH_SUFFIX = "/databases/";
    Context context;
    MyBean myBean;
    public static String DataBaseName1 = "FavoriteYoga.db";
    public static final String ID = "id";
    public static final String TABLENAME1 = "Favorite";
    public static String Name = "mudra_name";
    public static final String Method = "method";
    public static final String Benifit = "benifit";
    public static final String Image = "image";
    public static final String Id = "id";
    public static final String Categori_Id = "categori_id";

    private static final String PAssEncrypt = "Vithani123";

    private String TAG = getClass().getSimpleName();

    SQLiteDatabase database;

    public SQliteFavoriteHelper(Context context) {
        super(context, DataBaseName1, null, 1);
        this.context = context;
    }


    public void onCreate(SQLiteDatabase db) {
        String SQL = " CREATE TABLE IF NOT EXISTS " + TABLENAME1 + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Categori_Id + " REAL, " + Name + " TEXT, " + Method + " TEXT," + Benifit + " TEXT," + Image + " TEXT )";
        db.execSQL(SQL);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP T0ABLE IF EXISTS " + TABLENAME1;
        db.execSQL(sql);
        onCreate(db);
    }
    public void InsertData(MyBean myBean) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("mudra_name", myBean.getMudra_Name_Eng());// Name
        cv.put("method", myBean.getMethod_Eng());// method
        cv.put("benifit", myBean.getBenifits_Eng());// benifit
        cv.put("image", myBean.getYogaImg()); // image
        sqLiteDatabase.insert(TABLENAME1, null, cv);
        sqLiteDatabase.close();
    }

    public ArrayList<MyBean> displayData() {
        ArrayList<MyBean> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from " + TABLENAME1;
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            MyBean myBean = new MyBean();
            myBean.setMudra_Name_Eng(cursor.getString(2));
            myBean.setMethod_Eng(cursor.getString(3));
            myBean.setBenifits_Eng(cursor.getString(4));
            myBean.setYogaImg(cursor.getString(5));
            arrayList.add(myBean);
        }
        db.close();
        return arrayList;
    }


    public void Delet(MyBean myBean,String title) {
        //Open the database
        SQLiteDatabase database = getWritableDatabase();
        //Execute sql query to remove from database
        //NOTE: When removing by String in SQL, value must be enclosed with ''
        String sql="DELETE FROM Favorite WHERE "+Name+" = '"+ title+"'";
        database.execSQL(sql);
        //Close the database
        database.close();
    }

    public boolean isExist(MyBean myBean) {
        Boolean IsFavorite = false;

        SQLiteDatabase database = getWritableDatabase();
        String sql = "SELECT * FROM Favorite WHERE mudra_name = '" + myBean.getMudra_Name_Eng() + "' ";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String databaseVariable=cursor.getString(2);
            String myBeanVariable=myBean.getMudra_Name_Eng();
            if (databaseVariable.equals(myBeanVariable)) {
                IsFavorite = true;
            } else {
                IsFavorite = false;
            }
        }
        cursor.close();
        database.close();
        return IsFavorite;

    }

}
