package com.rm.oscatalog.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class BaseFragment extends Fragment {

    protected View mRootView;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
    }

    public View findViewById(int layoutId){
        return mRootView.findViewById(layoutId);
    }
}
