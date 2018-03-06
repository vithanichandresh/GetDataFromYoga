package com.example.krima.getdatafromyoga;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;

import static com.example.krima.getdatafromyoga.ImageLoaderFromDrawable.cancelPotentialWork;
import static com.example.krima.getdatafromyoga.R.id.imageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class imgOfYoga extends Fragment {

    ImageView img;
    ImageLoader IMAGE_LOADER;
    MyBean myBean;
    ZoomControls zoomControls;
    Context context;
    ArrayList<MyBean> arrayList;
    ImageLoaderFromDrawable mImageLoaderFromDrawable;
    private ScaleGestureDetector scaleGestureDetector;
    String TAG="tag";
    Matrix matrix;


    public imgOfYoga(Context context) {
        this.context=context;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.fragment_img_of_yoga, null);

        img=(ImageView) view.findViewById(R.id.YogaImg);
        zoomControls=(ZoomControls) view.findViewById(R.id.zoomControl1);

        try {
            if (getActivity().getIntent().getStringExtra("key") != null) {
                String key =getActivity().getIntent().getStringExtra("key");
                myBean = (MyBean) getActivity().getIntent().getSerializableExtra("item");
            } else {
                myBean = (MyBean) getActivity().getIntent().getSerializableExtra("favoriteItem");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String imgName = myBean.getYogaImg(); // specify here your image name fetched from db

        IMAGE_LOADER = MySingleton.getInstance(context).getImageLoader();
        int mImageWidth = context.getResources().getDimensionPixelSize(R.dimen.thumb_width);
        int mImageHeight = context.getResources().getDimensionPixelSize(R.dimen.thumb_height);
        mImageLoaderFromDrawable = new ImageLoaderFromDrawable(getActivity(), mImageWidth,mImageHeight );
        int image = context.getResources().getIdentifier(myBean.getYogaImg(), "drawable", context.getPackageName());
        Log.i(TAG, "onCreateView: "+image);

        mImageLoaderFromDrawable.loadBitmap(image,img);

        scaleGestureDetector=new ScaleGestureDetector(getContext(),new ScaleListener());
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                float x = img.getScaleX();
                float y = img.getScaleY();

                img.setScaleX((float) (x+1));
                img.setScaleY((float) (y+1));
            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                float x = img.getScaleX();
                float y = img.getScaleY();

                img.setScaleX((float) (x-1));
                img.setScaleY((float) (y-1));
            }
        });
        return view;
    }

    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();
            scaleFactor = Math.max(1,2);

            matrix.setScale(scaleFactor, scaleFactor);
            img.setImageMatrix(matrix);
            return true;
        }
    }

}
