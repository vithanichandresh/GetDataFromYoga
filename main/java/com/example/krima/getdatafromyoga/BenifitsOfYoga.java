package com.example.krima.getdatafromyoga;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class BenifitsOfYoga extends Fragment {

    TextView benifits;
    MyBean myBean;
    public BenifitsOfYoga() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_benifits_of_yoga,container,false);
        // Inflate the layout for this fragment
        benifits=(TextView) view.findViewById(R.id.benifit1);
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
        benifits.setText("Benifits :- \n"+myBean.getBenifits_Eng());
        return view;
    }

}
