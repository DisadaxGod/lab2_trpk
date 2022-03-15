package com.company;

public class Thread2 implements Runnable {

    private Buffer<Integer> buffer2;
    private Buffer<Integer> buffer1;

    public Thread2(Buffer<Integer> buffer2, Thread1 thread1) {
        this.buffer2 = buffer2;
        this.buffer1 = thread1.getBuffer1();
    }
    public Buffer<Integer> getBuffer2() {
        return buffer2;
    }

    @Override
    public void run() {
        System.out.println("Thread2_start");
        while (!Thread.currentThread().isInterrupted()) {

            synchronized (buffer1) {

                //если буфер генератора пуст, ждем сигнала
                while (buffer1.isEmpty() || buffer2.isFull()) {
                    try {
                        buffer1.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                synchronized (buffer2) {
                for (int i = 0; i < buffer1.size(); i++) {
                    //по заданию вычисляем значение функции
                    int x = 3* (buffer1.get(i)-100);
                    //добавляем результат вычисления во второй буфер
                    buffer2.add(x);
                    System.out.println("    Поток 2, результат вычисления: " + x);
                }
                //оповещаем буфер
                    buffer2.notifyAll();
                }
                // очищаем буфер генератора и оповещаем его
                buffer1.clear();
                buffer1.notifyAll();
            }
        }
        System.out.println("Thread2_end");
    }
}




