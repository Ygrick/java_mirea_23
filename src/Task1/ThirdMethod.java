package Task1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

class ThirdMethod {

    static long numOfOperations = 10000;
    static int numOfThreads = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) throws Exception {
        long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        ForkJoinPool pool = new ForkJoinPool(numOfThreads);
        Long result = pool.invoke(new MyFork(0, numOfOperations));
        long finish = System.currentTimeMillis();
        long time = finish - start;
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

    static class MyFork extends RecursiveTask<Long> {
        long from, to;

        public MyFork(long from, long to) {
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
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

