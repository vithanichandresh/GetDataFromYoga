package com.example.krima.getdatafromyoga;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.krima.getdatafromyoga.R.id.imageView;

public class MainActivity extends AppCompatActivity {

    Context context;
    ListView listView;
    SqliteHelper sqliteHelper;
    MyBean myBean;
    MyAdpt myAdpt;
    ArrayList<MyBean> arrayList;
    Toolbar toolbar;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-7559303174610793~8089319537");
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        listView = (ListView) findViewById(R.id.ListView1);
        toolbar = (Toolbar) findViewById(R.id.tBar);
        setSupportActionBar(toolbar);




        sqliteHelper = new SqliteHelper(MainActivity.this);

        arrayList = sqliteHelper.displayData();

        myAdpt = new MyAdpt(getApplicationContext(), arrayList);

        listView.setAdapter(myAdpt);

        listView.setTextFilterEnabled(true);
        // setupSerchView();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("item", arrayList.get(position));
                i.putExtra("key", "key1");
                startActivity(i);
            }
        });


    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ActionBar_favoritBtn:
                startActivity(new Intent(MainActivity.this, FavoriteList.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();
                } else {
                    listView.setFilterText(newText);
                }
                return true;
            }
        });
        return true;
    }


}
