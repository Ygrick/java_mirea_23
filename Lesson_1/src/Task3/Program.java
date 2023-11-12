package Task3;

/**
 * Главный класс Program, который запускает процесс создания и обработки файлов.
 */
public class Program {
    public static void main(String[] args) {
        // Инициализация очереди файлов
        FileQueue fileQueue = new FileQueue();

        // Создание экземпляров FileFactory и FileHandler
        FileFactory fileFactory = new FileFactory(fileQueue);
        FileHandler fileHandler = new FileHandler(fileQueue);

        // Запуск потоков для создания и обработки файлов
        new Thread(fileFactory).start();
        new Thread(fileHandler).start();
    }
}
