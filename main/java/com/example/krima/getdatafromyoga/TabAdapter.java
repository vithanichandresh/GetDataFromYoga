package com.example.krima.getdatafromyoga;

import android.app.Fragment;
import android.content.Context;
import android.support.*;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by KRIMA on 08-08-2017.
 */

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class TabAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<MyBean> arrayList;
    Context context;
    public TabAdapter(Context context,FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.context=context;
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        switch (position) {
            case 0:
                YogaMethodFragment tab1 = new YogaMethodFragment();
                return tab1;
            case 1:
                imgOfYoga tab3=new imgOfYoga(context);
                return  tab3;
            case 2:
                BenifitsOfYoga tab2 = new BenifitsOfYoga();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}