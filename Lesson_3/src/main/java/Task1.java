// Импорт необходимых библиотек.
import rx.Observable; // Для работы с реактивными потоками данных.
import java.util.Random; // Для генерации случайных чисел.

public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        // Создание объекта для генерации случайных чисел.
        Random rand = new Random();
        // Объявление переменных для температуры и уровня CO2.
        Integer temp=0, co2=0;
        // Объявление Observable для сигнализации.
        Observable<Integer> Signalization;

        // Бесконечный цикл для генерации и обработки сигналов.
        for (;;){
            // Генерация случайных значений для температуры и уровня CO2.
            temp = rand.nextInt(20) + 10; // Температура от 10 до 29.
            co2 = rand.nextInt(70) + 30;  // Уровень CO2 от 30 до 99.

            // Создание Observable из значений CO2 и температуры.
            Signalization = Observable.from(co2, temp);
            // Подписка на Observable и вывод значений в консоль.
            Signalization.subscribe(System.out::println);

            // Условия для вывода предупреждений.
            if (temp > 25 && co2 > 70){
                // Вывод сообщения о тревоге при высоких значениях температуры и CO2.
                System.out.println("ALARM!");
            }
            else if (temp > 25){
                // Вывод сообщения, если температура высока.
                System.out.println("Temp is not normal");
            } else if (co2 > 70){
                // Вывод сообщения, если уровень CO2 высок.
                System.out.println("Co2 is not normal");
            }

            // Ожидание 1 секунду перед следующей итерацией цикла.
            Thread.sleep(1000);
        }
    }
}
