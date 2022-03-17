package com.company;

public class Main {

    public static void main(String[] args) {
        // создаем потоки
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2(thread1);
        Thread3 thread3 = new Thread3(thread2);

        // запускаем потоки
        thread1.start();
        thread2.start();
        thread3.start();

        // ждем выполнения потока генератора
        try{
            thread1.join();
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        // прекращаем остальные потоки
        thread2.interrupt();
        thread3.interrupt();
    }
}
