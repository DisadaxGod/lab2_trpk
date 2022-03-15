package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Buffer <T extends Comparable<T>> {
    private int _size;
    private ArrayList<T> _buffer;

    //размер буфера
    public Buffer(int size) {
        this._size = size;
        this._buffer = new ArrayList<>(size);
    }

    //добавление в буфер числа
    public void add(T i) {
        _buffer.add(i);
    }

    //удаление числа из буфера
    public void remove(T val){
        _buffer.remove(val);
    }

    //очистка буфера
    public void clear(){
        _buffer.clear();
    }

    //заполнен ли буфер
    public boolean isFull(){
        return _buffer.size() == _size;
    }

    //пуст ли буфер
    public boolean isEmpty() {
        return _buffer.isEmpty();
    }

    //возврат размера буфера
    public int size() {
        return _buffer.size();
    }

    //возврат элемента i
    public T get(int i){
        return _buffer.get(i);
    }

    //поиск минимального значения в буфере
    public T min(){
        return Collections.min(_buffer);
    }
}
