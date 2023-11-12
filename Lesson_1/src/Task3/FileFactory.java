package Task3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Класс FileFactory отвечает за создание и добавление файлов в FileQueue.
 */
public class FileFactory implements Runnable {
    FileQueue fileQueue;
    // Очередь с предопределенными именами файлов
    static Queue<String> nameList = new LinkedList<String>();
    // Список возможных типов файлов
    String[] typeList = {"XML", "JSON", "XLS"};

    /**
     * Конструктор для инициализации FileQueue и добавления имен в nameList.
     */
    FileFactory(FileQueue fileQueue) {
        this.fileQueue = fileQueue;
        addAllElements();
    }

    /**
     * Метод для добавления предопределенных имен файлов в nameList.
     */
    static public void addAllElements() {
        for (int i = 0; i < 8; i++) {
            nameList.add("name" + i);
        }
    }

    @Override
    public void run() {
        Callable task = () -> {
            // Генерация случайного времени создания файла
            Integer time = (int) (Math.random() * 900 + 100);
            TimeUnit.MILLISECONDS.sleep(time);
            // Генерация случайного типа и размера файла
            String type = typeList[(int) (Math.random() * 2)];
            Integer size = (int) (Math.random() * 900 + 100);
            File file = new File(nameList.poll(), type, size);
            System.out.println("FileFactory: " +
                    file.name + "(Тип: "
                    + file.type +
                    ", размер: "
                    + file.size
                    + " байт)"
                    + " создан за "
                    + time
                    + " миллисекунд");
            return file;
        };
        // Создание и добавление файлов в FileQueue
        for (int i = 0; i < 8; i++) {
            FutureTask<File> file = new FutureTask<>(task);
            new Thread(file).start();
            try {
                fileQueue.put(file.get());
            } catch (Exception e) {

            }
        }
    }
}
