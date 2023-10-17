package Task3;

import java.util.concurrent.TimeUnit;

/**
 * Класс FileHandler отвечает за обработку файлов из FileQueue.
 */
public class FileHandler implements Runnable {
    FileQueue fileQueue;

    /**
     * Конструктор для инициализации FileQueue.
     */
    FileHandler(FileQueue fileQueue) {
        this.fileQueue = fileQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 8; i++) {
            // Получение файла из FileQueue
            File file = fileQueue.get();
            try {
                // Время обработки файла зависит от его размера
                Integer time = file.size * 7;
                TimeUnit.MILLISECONDS.sleep(time);
                System.out.println("FileHandler: Файл "
                        + file.name
                        + " обработан за "
                        + time + " миллисекунд");
            } catch (Exception e) {

            }
        }
    }
}
