package com.rm.oscatalog.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Content;
import com.rm.oscatalog.ui.adapter.ContentAdapter;
import com.rm.oscatalog.ui.adapter.OnItemClickListener;

import java.util.ArrayList;

import static com.rm.oscatalog.model.Content.TYPE_DOC;
import static com.rm.oscatalog.model.Content.TYPE_VIDEO;

public class PageContentFragment extends BaseFragment {

    private static final String KEY_DATA_SET = "dataSet";
    private static final String KEY_CONTENT_TYPE = "contentType";

    private static final String PACKAGE_VK = "com.vkontakte.android";
    private static final String PACKAGE_COOGLE_CHROME = "com.android.chrome";

    private ArrayList<Content> mPageContent;
    private ContentAdapter mContentAdapter;
    private String mContentType;

    public PageContentFragment() {
        // Required empty public constructor
    }

    public static PageContentFragment newInstance(ArrayList<Content> dataSet, String contentKey) {
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
        mContentType = getArguments().getString(KEY_CONTENT_TYPE);
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

        if (mContentAdapter == null)
            mContentAdapter = new ContentAdapter(
                    mPageContent,
                    mContentType,
                    R.layout.item_page_content
            );

        mContentAdapter.setOnItemClickListener(getItemClickListener());
        content.setAdapter(mContentAdapter);
    }

    private OnItemClickListener getItemClickListener() {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Content item = mPageContent.get(position);
                Intent viewIntent = getIntentForType(mContentType);
                viewIntent.setData(Uri.parse(item.getLink()));
                viewIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(viewIntent);
            }
        };
    }

    private Intent getIntentForType(String type) {
        PackageManager manager = getActivity().getPackageManager();
        boolean hasChrome = isInstalledPackage(manager, PACKAGE_COOGLE_CHROME);
        boolean hasVk = isInstalledPackage(manager, PACKAGE_VK);

        Intent viewIntent = new Intent(Intent.ACTION_VIEW);

        if (type.equals(TYPE_DOC) && hasChrome)
            viewIntent.setPackage(PACKAGE_COOGLE_CHROME);

        if (type.equals(TYPE_VIDEO)) {
            if (hasVk) viewIntent.setPackage(PACKAGE_VK);
            else if (hasChrome) viewIntent.setPackage(PACKAGE_COOGLE_CHROME);
        }

        return viewIntent;
    }

    private boolean isInstalledPackage(PackageManager manager, String packageName) {
        try {
            PackageInfo info = manager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return info != null;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
