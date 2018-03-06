package com.example.krima.getdatafromyoga;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentSkipListSet;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by KRIMA on 04-08-2017.
 */

public class MyAdpt extends BaseAdapter implements Filterable {

    ImageLoaderFromDrawable mImageLoaderFromDrawable;
    ImageLoader IMAGE_LOADER;
    Context context;
    ArrayList<MyBean> arrayList;
    ArrayList<MyBean> mFilteredList;
    public ArrayList<MyBean> orig;


    String TAG = getClass().getSimpleName();

    public MyAdpt(Context context, ArrayList<MyBean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        orig = arrayList;
        int mImageWidth = context.getResources().getDimensionPixelSize(R.dimen.thumb_width);
        int mImageHeight = context.getResources().getDimensionPixelSize(R.dimen.thumb_height);
        IMAGE_LOADER = MySingleton.getInstance(context).getImageLoader();
        mImageLoaderFromDrawable = new ImageLoaderFromDrawable(context, mImageWidth, mImageHeight);

    }

    public MyAdpt(ConcurrentSkipListSet classSearchArray, Context context, MainActivity mainActivity) {

    }


    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<MyBean> results = new ArrayList<MyBean>();

                if (orig == null)
                    orig = arrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final MyBean g : orig) {
                            if (g.getMudra_Name_Eng().toLowerCase()
                                    .contains(constraint.toString()) || g.getMudra_Name_Eng().toUpperCase().contains(constraint.toString()) || g.getMudra_Name_Eng().toString().contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                arrayList = (ArrayList<MyBean>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
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
        convertView = layoutInflater.inflate(R.layout.layout, null);

        TextView txtName = (TextView) convertView.findViewById(R.id.YogaName);
        TextView txtHindi = (TextView) convertView.findViewById(R.id.hindiName);
        CircleImageView img = (CircleImageView) convertView.findViewById(R.id.imgYoga);


        MyBean myBean = arrayList.get(position);

        Log.i(TAG, "mybean " + myBean.toString());
        if (myBean.getYogaImg() != null && myBean.getMudra_Name_Eng() != null && myBean.getHindiName() != null) {


            txtName.setText(myBean.getMudra_Name_Eng());
            int image = context.getResources().getIdentifier(myBean.getYogaImg(), "drawable", context.getPackageName());
            Log.i(TAG, "ListAdapter ========-------------> " + image);
            myBean.setSavedimage(image);
            txtHindi.setText(myBean.getHindiName());


            // Load image lazily
            mImageLoaderFromDrawable.loadBitmap(image, img);

        }


        return convertView;
    }

}
