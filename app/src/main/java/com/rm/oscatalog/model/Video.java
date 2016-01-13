package com.rm.oscatalog.model;

import android.os.Parcel;

// тип данных видео
// наследует посылочный тип
public class Video extends Content {

    // константа с дополнительной информацией о видео (источник)
    public static final String SOURCE = "Вконтакте";

    private final String preview; // обложка видео (название файла с изображением)
    private final int duration; // длительность (в секундах)

    // конструктор, принимающий в себя специальный тип данных(посылка),
    // в который упаковываются данные из объекта
    public Video(Parcel in) {
        super(in); // сохранение унаследованных полей

        // распаковывка данных из посылки и записываем их в поля обложки и длительности
        preview = in.readString();
        duration = in.readInt();
    }

    // метод для получения имени файла
    @Override // пометка, что метод отнаследован
    public String getName() {
        return this.name;
    }

    // метод для получения ссылки
    @Override // пометка, что метод отнаследован
    public String getLink() {
        return this.link;
    }

    // метод для получения обложки
    public String getPreview() {
        return this.preview;
    }

    // метод для получение длительности видео
    public int getDuration() {
        return this.duration;
    }

    // стандартная реализация для создания посылки
    public static final Creator<Video> CREATOR = new Creator<Video>() {

        // метод, создающий экземпляр класса из посылки
        @Override // пометка, что метод отнаследован
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        // метод создающий массив пустых объектов класса
        @Override // пометка, что метод отнаследован
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    // метод позволяющий сортировать данные в посылке
    // возвращает битовую маску, отображающую правила сортировки (ничего не сортировать)
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
        dest.writeString(TYPE_VIDEO); // запись ключа с типом данных
        super.writeToParcel(dest, flags); // запись унаследованных полей (имя, ссылка)
        dest.writeString(preview); // запись названия файла с обложкой
        dest.writeInt(duration); // запись длительности
    }
}
