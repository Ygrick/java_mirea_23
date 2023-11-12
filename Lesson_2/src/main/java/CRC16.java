import java.nio.ByteBuffer; // Импорт класса ByteBuffer для работы с буфером байтов

public class CRC16 {
    long hb_CRC; // Переменная для хранения высокозначащего байта CRC
    long lb_CRC; // Переменная для хранения низкозначащего байта CRC

    // Метод для получения высокозначащего байта CRC
    public long getHCrc() {
        return this.hb_CRC;
    }

    // Метод для получения низкозначащего байта CRC
    public long getLCrc() {
        return this.lb_CRC;
    }

    // Метод для вычисления CRC16
    public int cCRC16(ByteBuffer buf) {
        int i;
        int crc_value = 0; // Начальное значение CRC
        for (int len = 0; len < buf.position(); len++) {
            for (i = 0x80; i != 0; i >>= 1) {
                if ((crc_value & 0x8000) != 0) {
                    crc_value = (crc_value << 1) ^ 0x8005;
                } else {
                    crc_value = crc_value << 1;
                }
                if ((buf.get(len) & i) != 0) {
                    crc_value ^= 0x8005; // Применение полинома CRC
                }
            }
        }
        return crc_value; // Возвращение рассчитанного значения CRC
    }
}
