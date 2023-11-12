import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Task3 {

    public static void main(String[] args) throws IOException {
        // Инициализация массива байтов для хранения CRC-значения
        byte[] byteStr = new byte[4];

        // Создание объекта файла для чтения
        File file = new File("text.txt");

        // Создание потока ввода из файла
        FileInputStream fis = new FileInputStream(file);

        // Создание буфера байтов для хранения данных из файла
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // Чтение всех байтов из файла и помещение их в буфер
        byteBuffer.put(fis.readAllBytes());

        // Вычисление CRC16 для данных из буфера
        int crcRes = new CRC16().cCRC16(byteBuffer);
        // Вывод результата CRC16 в шестнадцатеричном формате
        System.out.println(Integer.toHexString(crcRes));

        // Разбиение CRC-значения на байты и сохранение в массив
        byteStr[0] = (byte) ((crcRes & 0x000000ff));
        byteStr[1] = (byte) ((crcRes & 0x0000ff00) >>> 8);

        // Вывод двух байтов CRC-значения в шестнадцатеричном формате
        System.out.printf("%02X\\n%02X", byteStr[0], byteStr[1]);
    }
}
