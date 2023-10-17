package Task1;

import java.util.concurrent.TimeUnit;

/**
 * Класс FirstMethod демонстрирует поиск минимального значения в массиве
 * с использованием одного потока и выводит затраченное время и использованный объем памяти.
 */
public class FirstMethod {

    public static void main(String[] args) throws InterruptedException {
        // Вычисление использованной памяти в начале выполнения
        long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // Инициализация массива и заполнение его случайными значениями
        int[] array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 10000));
        }

        // Засекаем время начала выполнения
        long start = System.currentTimeMillis();

        // Поиск минимального значения в массиве
        int min = array[0];
        for(int num : array) {
            if (num < min) min = num;
            TimeUnit.MILLISECONDS.sleep(1);
        }

        // Засекаем время завершения и вычисляем затраченное время
        long finish = System.currentTimeMillis();
        long time = finish - start;

        // Вывод результатов
        System.out.println("Задействовано "
                + usedBytes
                + " байт и "
                + " 1 поток,\n"
                + "Время выполнения : "
                + time
                + " милисеккунды,\n"
                + "Результат: "
                + min);
    }
}
