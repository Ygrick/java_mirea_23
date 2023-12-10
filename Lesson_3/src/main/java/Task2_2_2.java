import rx.Observable; // Импорт для работы с реактивными потоками данных.
import rx.Subscription; // Импорт для управления подписками на Observable.
import rx.observables.ConnectableObservable; // Импорт специального типа Observable.
import java.util.Random; // Импорт класса для генерации случайных чисел.

public class Task2_2_2 {
    static Random rand = new Random(); // Статический объект Random для генерации случайных чисел.

    public static void main(String[] args) throws InterruptedException {
        // Создание Observable объекта.
        Observable<Object> obs = Observable.create(subscriber ->
        {
            // Генерация и отправка 1000 последовательных чисел подписчикам.
            for (int i = 0; i < 1000; i++) {
                subscriber.onNext(i);
            }
        });

        // Подписка на Observable и вывод значений в консоль.
        Subscription s = obs.subscribe(System.out::println);
        // Ещё одна подписка на тот же Observable, также выводящая значения в консоль.
        Subscription s1 = obs.subscribe(System.out::println);
    }
}
