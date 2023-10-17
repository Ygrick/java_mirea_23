package Task2;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Task2 {

    public static void main(String []args) throws Exception{
        while (true) {
            Scanner in = new Scanner(System.in);
            Callable task = () -> {
                Integer time = (int) (Math.random() * 4 + 1);
                int num = in.nextInt();
                TimeUnit.SECONDS.sleep(time);
                System.out.println("Время задержки: " + time + " секунд");
                return num * num;
            };
            FutureTask<Integer> future = new FutureTask<>(task);
            new Thread(future).start();
            System.out.println("Квадрат числа: " + future.get());
        }
    }
}