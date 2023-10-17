package Task3;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс FileQueue представляет собой синхронизированную очередь файлов.
 */
public class FileQueue {
    Queue<File> queue = new LinkedList<>();

    /**
     * Метод для получения файла из очереди.
     * Если очередь пуста, поток будет ждать, пока файл не станет доступным.
     */
    public synchronized File get() {
        while (queue.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("FileQueue: Файл "
                + queue.peek().name +
                " удален из очереди");
        notify();
        return queue.poll();
    }

    /**
     * Метод для добавления файла в очередь.
     * Если размер очереди достигает 5, поток будет ждать, пока в очереди не освободится место.
     */
    public synchronized void put(File file) {
        while (queue.size() >= 5) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("FileQueue: Файл "
                + file.name +
                " добавлен в очередь");
        notify();
        queue.add(file);
    }
}
