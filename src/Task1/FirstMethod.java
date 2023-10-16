package Task1;

import java.util.concurrent.TimeUnit;

public class FirstMethod {

    public static void main(String[] args) throws InterruptedException {
        long usedBytes = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();

        int[] array;
        array = new int[10000];

        for (int i = 0; i < array.length; i++) {
            array[i] = ((int)(Math.random() * 10000));
        }

        long start = System.currentTimeMillis();
        int min = array[0];
        for(int num = 0; num < array.length; num++){
            if (num < min) min = num;
            TimeUnit.MILLISECONDS.sleep(1);
        }
        long finish = System.currentTimeMillis();
        long time = finish - start;
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
