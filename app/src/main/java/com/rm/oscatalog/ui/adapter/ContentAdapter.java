package com.rm.oscatalog.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.ui.holder.BaseViewHolder;
import com.rm.oscatalog.ui.holder.ViewHolderFactory;

import java.util.ArrayList;

public class ContentAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private final ArrayList<?> mDataSet;
    private final String mContentDataType;
    private final int mItemLayoutId;

    private OnItemClickListener mListener;

    public ContentAdapter(ArrayList<?> dataSet, String contentDataType, int id) {
        this.mItemLayoutId = id;
        this.mDataSet = dataSet;
        this.mContentDataType = contentDataType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(this.mItemLayoutId, parent, false);

        BaseViewHolder holder = ViewHolderFactory.build(mContentDataType, itemView);

        if (mListener != null) holder.setOnItemClickListener(mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}