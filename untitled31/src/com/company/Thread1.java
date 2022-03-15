package com.company;

public class Thread1 implements Runnable  {

    private Buffer<Integer> buffer1;

    public Buffer<Integer> getBuffer1() {
        return buffer1;
    }

    public Thread1(Buffer<Integer> buffer1){
        this.buffer1 = buffer1;
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
        for (int i = 0; i <1002; i++){
            synchronized (buffer1) {

                // если буфер полон, ждем сигнала
                while (buffer1.isFull()) {
                    try {
                        buffer1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //генерируем числа
                final int rnd = rnd(min, max);
                //добавляем числа в 1 буффер
                buffer1.add(rnd);
                System.out.println("Поток 1, сгенерированно число: " + rnd);
                // когда буфер заполнен, оповещаем об этом
                buffer1.notifyAll();
        }
        }
        System.out.println("Thread1_End");
    }
}
