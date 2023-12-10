import rx.Observable; // Импорт библиотеки rx для работы с реактивными потоками.
import rx.Subscription; // Импорт класса для работы с подписками на Observable.
import java.util.Random; // Импорт класса для генерации случайных чисел.

public class Task2_3_2 {
    static Random rand = new Random(); // Статический объект Random для генерации случайных чисел.

    public static void main(String[] args) throws InterruptedException {
        // Создание Observable объекта, который генерирует числа от 0 до 9.
        Observable<Object> obs = Observable.create(subscriber ->
        {
            for (int i = 0; i < 10; i++) {
                subscriber.onNext(i); // Отправка значений подписчикам.
            }
        });

        // Подписка на Observable, пропуская последние 5 значений.
        Subscription s = obs.skipLast(5).subscribe(System.out::println);
        // В результате будут выведены числа от 0 до 4.
    }
}
