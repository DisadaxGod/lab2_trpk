package com.company;

import java.util.ArrayList;

public class Thread1 extends Thread  {

    private ArrayList<Integer> buffer1 = new ArrayList<>();
    private final int bufferSize = 1000;

    public ArrayList<Integer> getBuffer1() {
        return buffer1;
    }


    //генератор случайных чисел
    public static int rnd(int min, int max)
    {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    @Override
    public void run() {
        System.out.println("Thread1_start");

        final int min = 100; // Минимальное число для диапазона
        final int max = 105; // Максимальное число для диапазона
        for (int i = 0; i <1001; i++){
            synchronized (buffer1) {

                // если буфер полон, ждем сигнала
                if (buffer1.size() == bufferSize) {
                    try {
                        buffer1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    //генерируем числа
                    final int rnd = rnd(min, max);
                    //добавляем числа в 1 буффер
                    buffer1.add(rnd);
                    System.out.println("Поток 1, сгенерированно число: " + rnd + " Количество " + i);
                    // когда буфер заполнен, оповещаем об этом
                    buffer1.notifyAll();
                }

            }

        }
        System.out.println("Thread1_End");
    }
}
