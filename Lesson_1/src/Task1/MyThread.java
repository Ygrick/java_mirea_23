package Task1;

import java.util.concurrent.CountDownLatch;

/**
 * Класс MyThread представляет собой поток, который выполняет поиск минимального значения
 * в переданном массиве и уменьшает счетчик CountDownLatch после завершения выполнения.
 */
class MyThread extends Thread {

    // Массив для поиска
    public int[] arr;
    // Текущее минимальное значение
    public int max;
    // Счетчик CountDownLatch для синхронизации потоков
    CountDownLatch latch;

    /**
     * Конструктор для инициализации массива, начального значения max и счетчика latch.
     */
    public MyThread(int[] arr, CountDownLatch latch) {
        this.arr = arr;
        this.max = arr[0];
        this.latch = latch;
    }

    // Геттер для получения найденного минимального значения
    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        // Поиск минимального значения в массиве
        for (int num : arr) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (num < max) {
                max = num;
            }
        }
        // Уменьшаем счетчик после завершения выполнения потока
        latch.countDown();
    }
}
