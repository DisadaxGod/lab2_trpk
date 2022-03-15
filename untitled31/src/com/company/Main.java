package com.company;

public class Main {

    public static final int N = 1000;

    public static void main(String[] args) {
        // создаем буфферы
        Buffer<Integer> genBuffer = new Buffer<>(N);
        Buffer<Integer> funBuffer = new Buffer<>(N);

        Thread1 generator = new Thread1(genBuffer);
        Thread2 function = new Thread2(funBuffer, generator);
        Thread3 print = new Thread3(function);

        // создаем потоки
        Thread thread1 = new Thread(generator);
        Thread thread2 = new Thread(function);
        Thread thread3 = new Thread(print);

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
