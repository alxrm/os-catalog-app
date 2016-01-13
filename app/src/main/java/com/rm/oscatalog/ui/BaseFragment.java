package com.rm.oscatalog.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

/* базовый фрагмент */
public class BaseFragment extends Fragment {

    protected View mRootView; // корневое представление фрагмента

    // метод создания представления фрагмента
    @Override // пометка, что метод отнаследован
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view; // сохранение корневого представления
    }

    // обёртка над неудобной конструкцией,
    // метод для поиска внутренних представлений у фрагмента
    public View findViewById(int layoutId){
        return mRootView.findViewById(layoutId);
    }
}
