package com.rm.oscatalog.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.PageData;
import com.rm.oscatalog.ui.adapter.SingleTypeAdapter;
import com.rm.oscatalog.utils.Pages;

import java.util.ArrayList;

public class PageContentFragment extends BaseFragment {

    private static final String KEY_DATA_SET = "dataSet";
    private static final String KEY_CONTENT_TYPE = "contentType";

    private ArrayList<PageData> mPageContent;
    private Class<? extends PageData> mContentType;
    private SingleTypeAdapter mSingleTypeAdapter;

    public PageContentFragment() {
        // Required empty public constructor
    }

    public static PageContentFragment newInstance(ArrayList<? extends PageData> dataSet,
                                                 String contentKey) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(KEY_DATA_SET, dataSet);
        args.putString(KEY_CONTENT_TYPE, contentKey);

        PageContentFragment fragment = new PageContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPageContent = getArguments().getParcelableArrayList(KEY_DATA_SET);
        if (mPageContent == null) throw new IllegalStateException("Fragment should receive data");

        String contentTypeKey = getArguments().getString(KEY_CONTENT_TYPE);
        if (contentTypeKey == null) throw new IllegalStateException("Content type should be defined");

        mContentType = Pages.getPageContentTypeByKey(contentTypeKey);
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

        RecyclerView content = (RecyclerView) findViewById(R.id.page_content);
        content.setLayoutManager(new LinearLayoutManager(getActivity()));

        if (mSingleTypeAdapter == null)
            mSingleTypeAdapter = new SingleTypeAdapter(
                    mPageContent,
                    mContentType,
                    R.layout.item_page_content
            );

        content.setAdapter(mSingleTypeAdapter);
    }
}
