package model.data;

import javafx.collections.ObservableListBase;

import java.util.ListIterator;

public class DataList<E> extends ObservableListBase<E> {

    @Override
    public E get(int index) {
        ListIterator<E> iterator = listIterator();
        E data = null;
        int i = 0;
        while (i < index) {
            if (iterator.hasNext()) {
                data = iterator.next();
            } else {
                throw new IndexOutOfBoundsException();
            }
            i++;
        }
        return data;
    }

    @Override
    public int size() {
        int i = 0;
        for (E e : this) {
            i++;
        }
        return i;
    }
}
