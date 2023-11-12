import java.io.IOException; // Импорт класса исключений
import java.nio.file.Files; // Импорт класса для работы с файлами
import java.nio.file.Path; // Импорт класса для работы с путями в файловой системе
import java.nio.file.Paths; // Импорт класса для создания объектов Path
import java.util.List; // Импорт класса для работы со списками

public class Task1 {

    public static void main(String[] args) {
        Path path = Paths.get("text.txt"); // Создаем объект Path для файла text.txt
        try {
            // Чтение всех строк из файла
            List contents = Files.readAllLines(path);

            // Вывод каждой строки файла в консоль
            for (Object content : contents) {
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Вывод ошибки в случае проблем с чтением файла
        }
    }
}
