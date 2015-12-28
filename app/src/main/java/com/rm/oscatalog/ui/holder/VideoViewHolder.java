package com.rm.oscatalog.ui.holder;

import android.os.Build;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Video;
import com.rm.oscatalog.utils.AssetsUtil;
import com.rm.oscatalog.utils.FormatUtil;

public class VideoViewHolder extends BaseViewHolder {

    private TextView mDuration;
    private TextView mName;
    private TextView mExtra;
    private RelativeLayout mPreview;

    public VideoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void createView(View root) {
        mPreview = (RelativeLayout) findViewById(R.id.item_preview_wrapper);
        mDuration = (TextView) findViewById(R.id.item_duration);
        mExtra = (TextView) findViewById(R.id.item_info_extra);
        mName = (TextView) findViewById(R.id.item_name);
    }

    @Override
    public <T> void bind(T model) {
        Video video = (Video) model;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            mPreview.setBackground(AssetsUtil.loadImageFromFile(video.preview));
        else
            mPreview.setBackgroundDrawable(AssetsUtil.loadImageFromFile(video.preview));

        mName.setText(video.name);
        mExtra.setText(Video.EXTRA);
        mDuration.setVisibility(View.VISIBLE);
        mDuration.setText(FormatUtil.formatSeconds(video.duration));
    }
}
