package com.rm.oscatalog.model;

import android.os.Parcel;

// тип данных документа
// наследует посылочный тип
public class Document extends Content {

    private final String ext; // расширение
    private final long size; // размер файла в байтах

    // конструктор, принимающий в себя специальный тип данных(посылка),
    // в который упаковываются данные из объекта
    public Document(Parcel in) {
        super(in); // сохранение унаследованных полей

        // распаковывка данных из посылки и записываем их в поля расширения и размера
        this.ext = in.readString();
        this.size = in.readLong();
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

    // метод для получения расширения
    public String getExt() {
        return this.ext;
    }

    // метод для получения размера файла
    public long getSize() {
        return this.size;
    }

    // стандартная реализация для создания посылки
    public static final Creator<Document> CREATOR = new Creator<Document>() {

        // метод, создающий экземпляр класса из посылки
        @Override // пометка, что метод отнаследован
        public Document createFromParcel(Parcel in) {
            return new Document(in);
        }

        // метод создающий массив пустых объектов класса
        @Override // пометка, что метод отнаследован
        public Document[] newArray(int size) {
            return new Document[size];
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
        dest.writeString(TYPE_DOC); // запись ключа с типом данных
        super.writeToParcel(dest, flags); // запись унаследованных полей (имя, ссылка)
        dest.writeString(ext); // запись расширения
        dest.writeLong(size); // запись размера
    }
}
