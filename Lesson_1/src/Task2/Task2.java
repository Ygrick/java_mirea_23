package Task2;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Класс Task2 принимает число от пользователя, задерживает выполнение на случайное количество
 * секунд (от 1 до 4), затем выводит это время задержки и квадрат введенного числа.
 */
public class Task2 {

    public static void main(String []args) throws Exception{
        // Начало бесконечного цикла
        while (true) {
            // Инициализация сканера для считывания ввода пользователя
            Scanner in = new Scanner(System.in);

            // Создание задачи, которая будет выполняться в отдельном потоке
            Callable task = () -> {
                // Генерация случайного времени задержки от 1 до 4 секунд
                Integer time = (int) (Math.random() * 4 + 1);

                // Считывание числа от пользователя
                int num = in.nextInt();

                // Задержка выполнения на сгенерированное количество секунд
                TimeUnit.SECONDS.sleep(time);

                // Вывод времени задержки
                System.out.println("Время задержки: " + time + " секунд");

                // Возврат квадрата введенного числа
                return num * num;
            };

            // Создание и запуск потока для выполнения задачи
            FutureTask<Integer> future = new FutureTask<>(task);
            new Thread(future).start();

            // Вывод квадрата введенного числа после выполнения задачи
            System.out.println("Квадрат числа: " + future.get());
        }
    }
}
