package com.example.krima.getdatafromyoga;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by KRIMA on 04-08-2017.
 */

public class FavoriteAdpt extends BaseAdapter {

    ImageLoaderFromDrawable mImageLoaderFromDrawable;
    ImageLoader IMAGE_LOADER;
    Context context;
    ArrayList<MyBean> arrayList;


    String TAG = getClass().getSimpleName();

    public FavoriteAdpt(Context context, ArrayList<MyBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        int mImageWidth = context.getResources().getDimensionPixelSize(R.dimen.thumb_width);
        int mImageHeight = context.getResources().getDimensionPixelSize(R.dimen.thumb_height);
        IMAGE_LOADER = MySingleton.getInstance(context).getImageLoader();
        mImageLoaderFromDrawable = new ImageLoaderFromDrawable(context, mImageWidth, mImageHeight);

    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.favoritelayout, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.YogaNameInFavoritLayout);
        TextView txtHindi = (TextView) convertView.findViewById(R.id.hindiNameInFavoritlayout);
        CircleImageView img = (CircleImageView) convertView.findViewById(R.id.imgYogaInFavoritLayout);


        MyBean myBean = arrayList.get(position);

        Log.i(TAG, "mybean " + myBean.toString());

        if (myBean.getYogaImg() == null){
            Toast.makeText(context, "Image Nathi", Toast.LENGTH_SHORT).show();
        }
        else {
            txtName.setText(myBean.getMudra_Name_Eng());
            int image = context.getResources().getIdentifier(myBean.getYogaImg(), "drawable", context.getPackageName());
            myBean.setSavedimage(image);
            txtHindi.setText(myBean.getHindiName());


            // Load image lazily
            mImageLoaderFromDrawable.loadBitmap(image, img);
        }


        return convertView;
    }

}
