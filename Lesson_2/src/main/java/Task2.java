import org.apache.commons.io.FileUtils; // Импорт библиотеки Apache Commons IO для работы с файлами

import java.io.*; // Импорт классов для ввода-вывода
import java.nio.channels.FileChannel; // Импорт класса для работы с файловыми каналами
import java.nio.file.Files; // Импорт класса для работы с файлами в Java NIO

public class Task2 {

    // Метод для копирования файла с использованием потоков
    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source); // Открытие потока чтения из исходного файла
            os = new FileOutputStream(dest); // Открытие потока записи в целевой файл
            byte[] buffer = new byte[1024]; // Буфер для чтения
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length); // Запись данных из буфера в файл
            }
        } finally {
            is.close(); // Закрытие потока чтения
            os.close(); // Закрытие потока записи
        }
    }

    // Метод для копирования файла с использованием класса Files
    private static void copyFileUsingJavaFiles(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    // Метод для копирования файла с использованием Apache Commons IO
    private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
        FileUtils.copyFile(source, dest);
    }

    // Метод для копирования файла с использованием FileChannel
    private static void copyFileUsingChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel(); // Открытие канала для чтения
            destChannel = new FileOutputStream(dest).getChannel(); // Открытие канала для записи
            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size()); // Передача данных из одного канала в другой
        } finally {
            sourceChannel.close(); // Закрытие канала чтения
            destChannel.close(); // Закрытие канала записи
        }
    }

    public static void main(String[] args) throws IOException {
        File source = new File("test_copy.pdf"); // Исходный файл
        File dest = new File("dst/test_copy.pdf"); // Целевой файл

        // Тестирование различных методов копирования и измерение времени выполнения
        long start = System.nanoTime();
        copyFileUsingJavaFiles(source, dest);
        System.out.println("Время копирования файла с помощью класса Files Java = " + ((System.nanoTime() - start) / 1000000) + "мс");

        source = new File("test_copy.pdf");
        dest = new File("dst/test_copy.pdf");
        start = System.nanoTime();
        copyFileUsingStream(source, dest);
        System.out.println("Время копирования файла с помощью потоков = " + ((System.nanoTime() - start) / 1000000) + "мс");

        source = new File("test_copy.pdf");
        dest = new File("dst/test_copy.pdf");
        start = System.nanoTime();
        copyFileUsingChannel(source, dest);
        System.out.println("Время копирования файла с помощью java.nio.FileChannel = " + ((System.nanoTime() - start) / 1000000) + "мс");

        source = new File("test_copy.pdf");
        dest = new File("dst/test_copy.pdf");
        start = System.nanoTime();
        copyFileUsingApacheCommonsIO(source, dest);
        System.out.println("Время копирования файла с помощью Apache Commons IO Copy = " + ((System.nanoTime() - start) / 1000000) + "мс");
    }
}
