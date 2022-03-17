package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Thread3 extends Thread  {

    private ArrayList<Integer> buffer2;
    private final int bufferSize = 1000;

    public Thread3 (Thread2 thread2){
        this.buffer2 = thread2.getBuffer();
    }

    @Override
    public void run() {
        System.out.println("Thread3_start");
        int min;
        //выполняем блок пока выполнение потока не будет прервано
        while(!Thread.currentThread().isInterrupted()){

            synchronized (buffer2){
                // выводим содержимое буфера, попутно освобождая его
                while (!buffer2.isEmpty()){
                    //извлекаем минимальное число из буфера и делим его на 3
                    min = Collections.min(buffer2);
                    //удаляем данное число из буфера
                    buffer2.remove((Integer)min);
                    System.out.println("        Поток 3, результат: " + min/3);
                }
                // оповещаем буфер
                buffer2.notifyAll();
            }
        }System.out.println("Thread3_End");
    }

}
