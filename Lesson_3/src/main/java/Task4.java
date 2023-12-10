import rx.Observable; // Импорт для работы с реактивными потоками данных.
import rx.functions.Func1; // Импорт функционального интерфейса для преобразования элементов в Observable.
import java.util.Objects;
import java.util.Random; // Импорт класса для генерации случайных чисел.
import java.util.concurrent.TimeUnit; // Импорт для работы с единицами времени.

public class Task4 {
    static File[] data = new File[100]; // Массив для хранения файлов.
    static String[] ext = {"XML", "JSON", "XLS"}; // Массив расширений файлов.
    static Random rand = new Random(); // Объект для генерации случайных чисел.

    // Метод для генерации Observable файлов с различными расширениями и размерами.
    static Observable<File> fileGenerator(){
        // Генерация задержки перед созданием файла.
        Observable.timer(rand.nextInt(900) + 100, TimeUnit.MILLISECONDS);
        // Возвращение Observable, содержащего файлы со случайными расширениями и размерами.
        return Observable.from(data).map(i -> new File(ext[rand.nextInt(3)], rand.nextInt(90) + 10));
    }

    // Метод для получения Observable с первыми пятью файлами из генератора.
    static Observable<File> Queue(){
        return fileGenerator().take(5);
    }

    // Обработчики для файлов различных типов, выводящие время обработки.
    static void XMLWorker(File obs){ System.out.print(obs.size * 9 + "мс\n"); }
    static void JSONWorker(File obs){ System.out.print(obs.size * 7 + "мс\n"); }
    static void XLSWorker(File obs){ System.out.print(obs.size * 8 + "мс\n"); }

    public static void main(String[] args) throws InterruptedException {
        // Подписка на Observable очереди файлов и их обработка в зависимости от типа файла.
        Observable.from(Queue()).subscribe(i -> i.flatMap((Func1<File, Observable<?>>) file -> {
            if (Objects.equals(file.type, "XML")){
                System.out.print(file.type + ": ");
                XMLWorker(file);
            }
            if (Objects.equals(file.type, "XLS")){
                System.out.print(file.type + ": ");
                XLSWorker(file);
            }
            if (Objects.equals(file.type, "JSON")){
                System.out.print(file.type + ": ");
                JSONWorker(file);
            }
            return i; // Возврат Observable для подписки.
        }).subscribe());
    }
}
