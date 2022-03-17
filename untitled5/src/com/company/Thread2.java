package com.company;

import java.util.ArrayList;

public class Thread2 extends Thread  {


    private ArrayList<Integer> buffer1;
    private ArrayList<Integer> buffer2 = new ArrayList<>();
    private final int bufferSize = 1000;

    public Thread2(Thread1 thread1) {
        this.buffer1 = thread1.getBuffer1();
    }


    @Override
    public void run() {
        System.out.println("Thread2_start");

        while (!Thread.currentThread().isInterrupted()) {

            synchronized (buffer1) {

                //если буфер генератора пуст, ждем сигнала
                if (buffer1.isEmpty() || buffer2.size()==bufferSize) {
                    try {
                        buffer1.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }else {
                    synchronized (buffer2) {

                            //по заданию вычисляем значение функции
                        int x = 3* (buffer1.get(0)-100);
                            //добавляем результат вычисления во второй буфер
                        buffer2.add(x);
                        buffer1.remove(0);

                            System.out.println("    Поток 2, результат вычисления: " + x);
                        //}
                        //оповещаем буфер
                        buffer2.notifyAll();
                    }
                    // очищаем буфер
                    buffer1.notifyAll();
                }

            }
        }
        System.out.println("Thread2_end");
    }
    public ArrayList<Integer> getBuffer(){
        return buffer2;
    }
}
