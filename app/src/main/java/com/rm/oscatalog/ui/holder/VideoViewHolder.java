package com.rm.oscatalog.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Video;
import com.rm.oscatalog.utils.FormatUtil;

public class VideoViewHolder extends BaseViewHolder {

    private TextView mDuration;
    private TextView mName;
    private TextView mExtra;

    public VideoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void createView(View root) {
        mDuration = (TextView) findViewById(R.id.item_duration);
        mExtra = (TextView) findViewById(R.id.item_info_extra);
        mName = (TextView) findViewById(R.id.item_name);
    }

    @Override
    public <T> void bind(T model) {
        Video video = (Video) model;

        // TODO implement preview loading from YouTube
        mDuration.setVisibility(View.VISIBLE);
        mDuration.setText(FormatUtil.formatSeconds(video.duration));
        mName.setText(video.name);
        mExtra.setText(Video.EXTRA);
    }
}
