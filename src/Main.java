import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        int[] array;
        array = new int[10000];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 12) - 15);
        }
        task1(array);
    }

    public static void task1(int[] arr){
        Object lock = new Object();
        ExecutorService executorService =
                Executors.newCachedThreadPool();

        Callable<Integer> task = () -> {
            int min = arr[0];
            for(int num = 0; num < arr.length; num++){
                System.out.println(123);
                if (num < min) min = num;
                lock.wait(1);
                System.out.println(min);
            }
            return min;
        };
        for (int i = 0; i < 5; i++) {
            executorService.submit(task);
        }
        executorService.shutdown();
    }

    Integer compare(int num, int min){
        if (num < min) min = num;
        return min;
    }


}
