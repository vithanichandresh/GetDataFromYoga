package com.example.krima.getdatafromyoga;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class FavoriteList extends AppCompatActivity {

    ListView listView;
    FavoriteAdpt myAdpt;
    ArrayList<MyBean> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        listView=(ListView) findViewById(R.id.FavoriteList);
        TextView textView=(TextView) findViewById(R.id.No_Item_txt);


        SQliteFavoriteHelper helper=new SQliteFavoriteHelper(FavoriteList.this);

        arrayList=helper.displayData();


        myAdpt=new FavoriteAdpt(FavoriteList.this,arrayList);

        listView.setAdapter(myAdpt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(FavoriteList.this,Main2Activity.class);
                i.putExtra("favoriteItem",arrayList.get(position));
                startActivity(i);
            }
        });
    }
}
