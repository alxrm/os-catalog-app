package com.rm.oscatalog.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rm.oscatalog.R;
import com.rm.oscatalog.model.Video;
import com.rm.oscatalog.utils.AssetsUtil;
import com.rm.oscatalog.utils.FormatUtil;

public class VideoViewHolder extends BaseViewHolder {

    private TextView mDuration; // представление с длительностью
    private TextView mName; // имя
    private TextView mSource; // источник
    private ImageView mPreview; // обложка

    // конструктор, принимающий представление
    public VideoViewHolder(View itemView) {
        super(itemView); // переиспользование логики из родительского конструктора
    }

    @Override // пометка, что метод отнаследован
    public void createView(View root) {

        // получение представлений из вёрстки
        mPreview = (ImageView) findViewById(R.id.item_preview);
        mDuration = (TextView) findViewById(R.id.item_duration);
        mSource = (TextView) findViewById(R.id.item_info_extra);
        mName = (TextView) findViewById(R.id.item_name);
    }

    @Override // пометка, что метод отнаследован
    public <T> void bind(T model) {
        Video video = (Video) model;

        // загрузка изображения обложки
        mPreview.setImageBitmap(null);
        mPreview.setImageDrawable(AssetsUtil.loadImageFromFile(video.getPreview()));

        mName.setText(video.getName()); // привязка имени
        mSource.setText(Video.SOURCE); // привязка источника

        // делаем видимой строку с длительностью(мы её прячем, если элемент это документ)
        mDuration.setVisibility(View.VISIBLE);
        mDuration.setText(FormatUtil.formatSeconds(video.getDuration())); // привязка длительности
    }
}
