package Task1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * Класс ThirdMethod демонстрирует использование ForkJoinPool для выполнения вычислений
 * в нескольких потоках и выводит затраченное время и использованный объем памяти.
 */
class ThirdMethod {

    static long numOfOperations = 10000;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        // Вычисление использованной памяти в начале выполнения
        long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        // Засекаем время начала выполнения
        long start = System.currentTimeMillis();

        // Использование ForkJoinPool для параллельного выполнения задач
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        Long result = pool.invoke(new MyFork(0, numOfOperations));

        // Засекаем время завершения и вычисляем затраченное время
        long finish = System.currentTimeMillis();
        long time = finish - start;

        // Вывод результатов
        System.out.println("Задействовано "
                + usedBytes
                + " байт и "
                + numOfThreads
                + " потоков,\n"
                + "Время выполнения : "
                + time
                + " милисекунды,\n"
                + "Результат: "
                + result);
    }

    /**
     * Вложенный класс MyFork представляет собой задачу для выполнения в ForkJoinPool.
     */
    static class MyFork extends RecursiveTask<Long> {
        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // Если задача маленькая, выполняем ее в текущем потоке
            if ((to - from) <= numOfOperations / numOfThreads) {
                long j = 0;
                for (long i = to; i < from; i++) {
                    if(j < i) {
                        try {
                            j=i;
                            TimeUnit.MILLISECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return j;
            } else {
                // Если задача большая, разбиваем ее на две части и выполняем параллельно
                long middle = (to + from) / 2;
                MyFork firstHalf = new MyFork(from, middle);
                firstHalf.fork();
                MyFork secondHalf = new MyFork(middle + 1, to);
                long secondValue = secondHalf.compute();
                return firstHalf.join() + secondValue;
            }
        }
    }
}
