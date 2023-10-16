package Task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SecondMethod {
    public static void main(String[] args) throws InterruptedException {
        int[] array;
        array = new int[10000];

        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 10000));
        }
        findMaxInSeveralThreads(array, 4);
    }
    public static Integer findMaxInSeveralThreads(int[] arr, int numOfThreads)
            throws InterruptedException {
        List<MyThread> threads = new ArrayList<>();
        CountDownLatch latch = new CountDownLatch(numOfThreads);
        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfThreads; i++) {
            MyThread thread = new MyThread(
                    Arrays.copyOfRange(arr, i * 1000 / numOfThreads, (i + 1) * 1000 / numOfThreads), latch);
            threads.add(thread);
            thread.start();
        }
        latch.await();
        int result = threads.stream().max(Comparator.comparing(MyThread::getMax)).get().max;
        long end = System.currentTimeMillis();
        long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
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
