package Task3;

/**
 * Класс File представляет собой модель файла с атрибутами имени, типа и размера.
 */
public class File {
    String name;
    String type;
    Integer size;

    /**
     * Конструктор для инициализации атрибутов файла.
     */
    public File(String name, String type, Integer size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    // Геттеры и сеттеры для атрибутов
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
