package com.example.krima.getdatafromyoga;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS;

public class Main2Activity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ImageView imgTopView;
    ViewPager viewPager;
    ImageLoader IMAGE_LOADER;
    Button share;
    ImageLoaderFromDrawable mImageLoaderFromDrawable;
    TextView YogaNameE, YogaNameG;
    ArrayList<MyBean> arrayList;
    MyBean myBean;
    FloatingActionButton favoriteBtn;
    SqliteHelper sqliteHelper;
    SQLiteDatabase db;
    private String[] tabs = {"Method", "Image", "benifit"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        try {
            if (getIntent().getStringExtra("key") != null) {
                String key = getIntent().getStringExtra("key");
                myBean = (MyBean) getIntent().getSerializableExtra("item");
            } else {
                myBean = (MyBean) getIntent().getSerializableExtra("favoriteItem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        sqliteHelper = new SqliteHelper(Main2Activity.this);
        favoriteBtn = (FloatingActionButton) findViewById(R.id.FloatingFavorite);
        share = (Button) findViewById(R.id.shareBtn);
        imgTopView = (ImageView) findViewById(R.id.toolbar);
        YogaNameE = (TextView) findViewById(R.id.YogaNameEng);
        YogaNameG = (TextView) findViewById(R.id.YogaNAmeGuj);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Method"));
        tabLayout.addTab(tabLayout.newTab().setText("Image"));
        tabLayout.addTab(tabLayout.newTab().setText("Benifits"));

        YogaNameE.setText(myBean.getMudra_Name_Eng());
        YogaNameG.setText(myBean.getHindiName());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.ViewPager1);

        TabAdapter adapter = new TabAdapter(Main2Activity.this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        IMAGE_LOADER = MySingleton.getInstance(Main2Activity.this).getImageLoader();
        int mImageWidth = Main2Activity.this.getResources().getDimensionPixelSize(R.dimen.thumb_width);
        int mImageHeight = Main2Activity.this.getResources().getDimensionPixelSize(R.dimen.thumb_height);
        mImageLoaderFromDrawable = new ImageLoaderFromDrawable(Main2Activity.this, mImageWidth, mImageHeight);
        int image = Main2Activity.this.getResources().getIdentifier(myBean.getYogaImg(), "drawable", Main2Activity.this.getPackageName());

        mImageLoaderFromDrawable.loadBitmap(image, imgTopView);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filePath = app.sourceDir;

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("application/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                startActivity(Intent.createChooser(intent, "Share app via"));

            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2Activity.this, MainActivity.class));
            }
        });
        favoriteBtn.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                               final SQliteFavoriteHelper myHelper = new SQliteFavoriteHelper(getApplicationContext());
                                               if (myHelper.isExist(myBean)){
                                                   AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                                                   builder.setMessage("Remove From Favorite ?");
                                                   builder.setCancelable(true);
                                                   builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           myHelper.Delet(myBean, myBean.getMudra_Name_Eng());
                                                           favoriteBtn.setImageResource(R.drawable.ic_unfavorite);
                                                       }
                                                   });

                                                   builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                       @Override
                                                       public void onClick(DialogInterface dialog, int which) {
                                                           dialog.cancel();
                                                       }
                                                   });
                                                   builder.show();
                                               }
                                               else {
                                                   myHelper.InsertData(myBean);
                                                   favoriteBtn.setImageResource(R.drawable.ic_favorite);
                                               }
                                           }

                                       }
        );


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        super.onBackPressed();
    }
}
