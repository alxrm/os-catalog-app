package com.rm.oscatalog.ui.adapter;

import android.view.View;

// интерфейс слушатель нажатия, класс,
// его реализовавший, сможет обрабатывать событие нажатия
public interface OnItemClickListener {
    void onItemClick(View v, int position); // метод вызываемый при нажатии
}
