package com.rm.oscatalog.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Document;
import com.rm.oscatalog.utils.FormatUtil;

public class DocumentViewHolder extends BaseViewHolder {

    private TextView mName;
    private TextView mExt;
    private TextView mSize;

    public DocumentViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void createView(View root) {
        mName = (TextView) findViewById(R.id.item_name);
        mExt = (TextView) findViewById(R.id.item_ext);
        mSize = (TextView) findViewById(R.id.item_info_extra);
    }

    @Override
    public <T> void bind(T model) {
        Document document = (Document) model;

        mName.setText(document.name);
        mSize.setText(FormatUtil.formatBytes(document.size));
        mExt.setVisibility(View.VISIBLE);
        mExt.setText(document.ext);
    }
}
