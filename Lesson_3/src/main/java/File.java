// Класс File для представления файла с типом и размером.
public class File {
    // Переменные для хранения типа и размера файла.
    String type;
    int size;

    // Конструктор для инициализации нового файла с заданным типом и размером.
    public File(String type, int size){
        this.type = type; // Инициализация типа файла.
        this.size = size; // Инициализация размера файла.
    }

    // Метод для получения типа файла.
    public String getType() {
        return type;
    }
}
