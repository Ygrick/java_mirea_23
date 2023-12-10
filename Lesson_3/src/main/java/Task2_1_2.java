import rx.Observable; // Импорт библиотеки rx для работы с реактивными потоками.
import java.util.Random; // Импорт класса для генерации случайных чисел.

public class Task2_1_2 {
    // Статический объект Random для генерации случайных чисел.
    static Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {
        // Переменная для хранения сгенерированных чисел.
        int num = 0;

        // Цикл для генерации и обработки 1000 случайных чисел.
        for (int i = 0; i < 1000; i++) {
            // Генерация случайного числа от 0 до 999.
            num = rand.nextInt(1000);
            // Создание Observable из сгенерированного числа, фильтрация и вывод в консоль.
            Observable.from(num)
                    .filter(num2 -> num2 > 500) // Фильтрация чисел больше 500.
                    .subscribe(System.out::println); // Вывод подходящих чисел в консоль.
        }
    }
}
