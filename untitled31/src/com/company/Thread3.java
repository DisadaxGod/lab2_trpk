package com.company;

public class Thread3 implements Runnable {
    private Buffer<Integer> buffer2;

    public Thread3 (Thread2 thread2){
        this.buffer2 = thread2.getBuffer2();
    }

    @Override
    public void run() {
        System.out.println("Thread3_start");
        int min;
        //выполняем блок пока выполнение потока не будет прервано
        while(!Thread.currentThread().isInterrupted()){
            int i = 0;
            synchronized (buffer2){
                // выводим содержимое буфера, попутно освобождая его
                while (!buffer2.isEmpty()){
                    //извлекаем минимальное число из буфера и делим его на 3
                    min = buffer2.min();
                    //удаляем данное число из буфера
                    buffer2.remove(min);
                    System.out.println("        Поток 3, результат: " + min/3);
                    i++;
                }
                // оповещаем буфер
                buffer2.notifyAll();
            }
        }System.out.println("Thread3_End");
    }
}
