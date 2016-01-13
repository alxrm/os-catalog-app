package com.rm.oscatalog.model;

import android.os.Parcel;
import android.os.Parcelable;

// базовый класс для типов данных, отображаемых на странице,
// реализует посылочный тип
public abstract class Content implements Parcelable {

    // константы с ключом для определения типа данных
    public static final String  TYPE_VIDEO = "VIDEO";
    public static final String TYPE_DOC = "DOC";

    // поля, которые могут быть и у видео и у документов, они их наследуют
    protected final String name; // имя файла
    protected final String link; // ссылка на его скачивание

    // конструктор, принимающий в себя специальный тип данных(посылка),
    // в который упаковываются данные из объекта
    protected Content(Parcel in) {

        // распаковка данных из посылки и записываем их в поля имени и ссылки
        name = in.readString();
        link = in.readString();
    }

    // абстрактный метод для получения имени, дочерние классы его наследуют и реализуют
    public abstract String getName();

    // абстрактный метод для получения ссылки, дочерние классы его наследуют и реализуют
    public abstract String getLink();

    // стандартная реализация для создания посылки
    public static final Creator<Content> CREATOR = new Creator<Content>() {

        // метод, создающий экземпляр класса из посылки
        @Override // пометка, что метод отнаследован
        public Content createFromParcel(Parcel in) {
             /*
             по ключу с типом данных(одной из констант) мы получаем экземпляр класса
             поскольку класс абстрактный, экземпляр можно получить только у его наследников
             */
            return Content.findContentType(in);
        }

        // метод создающий массив пустых объектов класса
        @Override // пометка, что метод отнаследован
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    private static Content findContentType(Parcel in) {
        switch (in.readString()) {
            case TYPE_VIDEO: return new Video(in);
            case TYPE_DOC: return new Document(in);
            default: throw new IllegalArgumentException("Cannot find content contentType");
        }
    }

    /*
    метод позволяющий сортировать данные в посылке
    возвращает битовую маску, отображающую правила сортировки (ничего не сортировать)
    */
    @Override // пометка, что метод отнаследован
    public int describeContents() {
        return 0;
    }

    /*
    метод записи данных в посылку, передаваемую через параметры
    флаги для определения, что данные, которые мы записываем,
    являются результатом выполнения функции (не используется)
    */
    @Override // пометка, что метод отнаследован
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name); // запись имени
        dest.writeString(link); // запись ссылки
    }
}
