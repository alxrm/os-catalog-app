package com.rm.oscatalog.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rm.oscatalog.ui.adapter.OnItemClickListener;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {

    private View mRoot;
    private OnItemClickListener mClickListener;

    public BaseViewHolder(View itemView) {
        super(itemView);

        mRoot = itemView;
        createView(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mClickListener != null)
            this.mClickListener.onItemClick(v, getAdapterPosition());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    protected View findViewById(int id) {
        return mRoot.findViewById(id);
    }

    public abstract void createView(View root);

    public abstract <T> void bind(T model);
}