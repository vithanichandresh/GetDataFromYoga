package com.example.krima.getdatafromyoga;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by KRIMA on 04-08-2017.
 */

public class SqliteHelper extends SQLiteOpenHelper {

    String TAG=getClass().getSimpleName();
    private static String DB_PATH = "/databases/";
    public static String DataBaseName = "yoga.db";
    public static final String TABLENAME = "Yoga";
    Context mContext;
    //public boolean dbExist=checkDatabase();

    SQLiteDatabase myDatabase;

    public SqliteHelper(Context context) {
        super(context,DataBaseName,null,1);
        this.mContext=context;
        Log.d(TAG, "SqliteHelper: is called");
        try {
            CopyDataBaseFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void CopyDataBaseFromAsset() throws IOException {
        InputStream myInput = mContext.getAssets().open(DataBaseName);
// Path to the just created empty db
        String outFileName = getDatabasePath();
        // if the path doesn't exist first, create it

        File f = new File(mContext.getApplicationInfo().dataDir + DB_PATH);
        if (!f.exists())
            f.mkdir();
// Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
// transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

// Close the streams

        myOutput.flush();

        myOutput.close();

        myInput.close();
    }

    public String getDatabasePath() {
        return mContext.getApplicationInfo().dataDir + DB_PATH
                + DataBaseName;
    }

    public ArrayList<MyBean> displayData() {
        ArrayList<MyBean> arrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from "+ TABLENAME+" WHERE Mudra_Name_Eng IS NOT NULL AND Mudra_Name_Hindi IS NOT NULL AND Img IS NOT NULL";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            if (cursor.getString(2) != null){
                if (cursor.getString(5)!= null){
                    if (cursor.getString(12)!= null){
                        MyBean myBean = new MyBean();
                        myBean.setMudra_Name_Eng(cursor.getString(2));
                        myBean.setMethod_Eng(cursor.getString(3));
                        myBean.setBenifits_Eng(cursor.getString(4));
                        myBean.setYogaImg(cursor.getString(12));
                        myBean.setHindiName(cursor.getString(5));
                        arrayList.add(myBean);
                    }
                }
            }
        }
        db.close();
        return arrayList;
    }



}
