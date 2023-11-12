package Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Класс SecondMethod демонстрирует поиск минимального значения в массиве
 * с использованием нескольких потоков и выводит затраченное время и использованный объем памяти.
 */
public class SecondMethod {

    public static void main(String[] args) throws InterruptedException {
        // Инициализация массива и заполнение его случайными значениями
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 10000));
        }
        findMaxInSeveralThreads(array, 4);
    }

    /**
     * Метод для поиска минимального значения в массиве с использованием указанного количества потоков.
     */
    public static Integer findMaxInSeveralThreads(int[] arr, int numOfThreads)
            throws InterruptedException {
        List<MyThread> threads = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(numOfThreads);

        // Засекаем время начала выполнения
        long start = System.currentTimeMillis();

        // Создание и старт потоков
        for (int i = 0; i < numOfThreads; i++) {
            MyThread thread = new MyThread(
                    Arrays.copyOfRange(arr, i * 1000 / numOfThreads, (i + 1) * 1000 / numOfThreads), latch);
            threads.add(thread);
            thread.start();
        }

        // Ожидание завершения всех потоков
        latch.await();

        // Нахождение минимального значения среди всех потоков
        int result = threads.stream().max(Comparator.comparing(MyThread::getMax)).get().max;

        // Засекаем время завершения и вычисляем затраченное время
        long end = System.currentTimeMillis();
        long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // Вывод результатов
        System.out.println("Задействовано "
                + usedBytes
                + " байт и "
                + numOfThreads
                + " поток/ов,\n"
                + "Время выполнения : "
                + (end - start)
                + " милисекунды,\n"
                + "Результат: "
                + result);

        return result;
    }
}
