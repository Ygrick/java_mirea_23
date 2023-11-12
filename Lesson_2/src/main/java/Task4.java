import org.apache.commons.lang3.StringUtils; // Импорт библиотеки Apache Commons Lang для работы со строками

import java.io.IOException;
import java.nio.file.*; // Импорт классов для работы с файловой системой

public class Task4 {

    public Task4() throws IOException, InterruptedException {
        // Создание объектов Path для работы с папкой dst
        Path path = Paths.get("dst");

        // Создание службы для отслеживания изменений в файловой системе
        WatchService watchService = FileSystems.getDefault().newWatchService();

        // Чтение содержимого файла в папке dst
        byte[] bytes = Files.readAllBytes(Paths.get("dst/tst"));
        String fileContent = new String(bytes);

        // Регистрация пути в службе для отслеживания событий создания, удаления и изменения файлов
        path.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        WatchKey key;
        // Цикл, ожидающий событий от службы отслеживания
        while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                // Вывод информации о событии и задействованном файле
                System.out.println(
                        "Событие:" + event.kind()
                                + ". Задействованный файл: " + event.context() + ".");
                // Проверка, был ли файл изменен
                if (event.kind().name().equals(StandardWatchEventKinds.ENTRY_MODIFY.name())) {
                    byte[] bytes1 = Files.readAllBytes(Paths.get("dst/tst"));
                    String fileContent1 = new String(bytes1);
                    // Вывод разницы между старым и новым содержимым файла
                    if (fileContent.length() < fileContent1.length()) {
                        System.out.println(StringUtils.difference(fileContent, fileContent1));
                    } else {
                        System.out.println(StringUtils.difference(fileContent1, fileContent));
                    }
                }
            }
            // Сброс ключа для следующего ожидания событий
            key.reset();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // Создание экземпляра класса для начала отслеживания
        new Task4();
    }
}
