package com.rm.oscatalog.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.PageData;

import java.util.ArrayList;

public class CollectionFragment<T extends PageData> extends BaseFragment {

    private static final String KEY_DATA_SET = "dataSet";
    private ArrayList<T> mDataSet;

    public CollectionFragment() {
        // Required empty public constructor
    }

    public static CollectionFragment newInstance(ArrayList<? extends PageData> dataSet) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_DATA_SET, dataSet);

        CollectionFragment fragment = new CollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDataSet = getArguments().getParcelableArrayList(KEY_DATA_SET);
        if (mDataSet == null) throw new IllegalStateException("Fragment must receive data");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
