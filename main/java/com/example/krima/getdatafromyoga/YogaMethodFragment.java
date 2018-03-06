package com.example.krima.getdatafromyoga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class YogaMethodFragment extends Fragment {
    MyBean myBean;
    String TAG=getClass().getSimpleName();
    public YogaMethodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
     View view=inflater.inflate(R.layout.yoga_method_fragment,container,false);
        // Inflate the layout for this fragment
        TextView method=(TextView) view.findViewById(R.id.method1);

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
      //  Log.i(TAG, "onCreateView:"+getActivity().getIntent().getSerializableExtra("item"));
        method.setText("Method :- \n"+myBean.getMethod_Eng());


        return view;
    }

}
