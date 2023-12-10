import rx.Observable; // Импорт для работы с реактивными потоками данных.
import java.util.Random; // Импорт класса для генерации случайных чисел.

public class Task3 {
    // Статический массив для хранения данных о друзьях пользователей.
    static UserFriend[] data = new UserFriend[100];
    static Random rand = new Random(); // Объект для генерации случайных чисел.

    // Метод для получения Observable, который фильтрует друзей пользователя по ID.
    static Observable<UserFriend> getFriends(int userId){
        return Observable.from(data).filter(x -> x.userId == userId);
    }

    public static void main(String[] args) throws InterruptedException {
        // Инициализация массива data случайными объектами UserFriend.
        for (int i = 0; i < 100; i++){
            data[i] = new UserFriend(rand.nextInt(), rand.nextInt(100));
        }

        // Массив для хранения ID пользователей.
        int[] Ids = new int [100];

        // Заполнение массива Ids случайными ID пользователей.
        for (int i = 0; i < 100; i++)
            Ids[i] = rand.nextInt(1000);

        // Создание Observable из диапазона 0 до длины массива Ids, получение ID пользователя и подписка на Observable друзей.
        Observable.range(0, Ids.length)
                .map(i -> Ids[i]) // Преобразование индекса в ID пользователя.
                .subscribe(i -> getFriends(i) // Получение Observable друзей пользователя.
                        .subscribe(l -> System.out.println(l.userId))); // Вывод ID друзей в консоль.
    }
}
