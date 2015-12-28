package com.rm.oscatalog.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rm.oscatalog.ui.holder.BaseViewHolder;
import com.rm.oscatalog.ui.holder.ViewHolderFactory;

import java.util.ArrayList;

public class SingleTypeAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private OnItemClickListener mListener;
    private ArrayList<?> mContent;
    private final Class<?> mContentDataType;
    private final int mItemLayoutId;

    public SingleTypeAdapter(ArrayList<?> content, Class<?> contentDataType, int id) {
        this.mItemLayoutId = id;
        this.mContent = content;
        this.mContentDataType = contentDataType;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(this.mItemLayoutId, parent, false);

        BaseViewHolder holder = ViewHolderFactory.build(mContentDataType, itemView);

        if (mListener != null)
            holder.setOnItemClickListener(mListener);

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mContent.get(position));
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}