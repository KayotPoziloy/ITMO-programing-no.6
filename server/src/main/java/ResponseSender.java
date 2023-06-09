import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * Отправка ответов с использованием DatagramSocket
 */
public class ResponseSender {
    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();
    /**
     * Сокет сервера для отправки данных
     */
    private final DatagramSocket serverSocket;

    /**
     * Конструктор класса
     * @param serverSocket - сокет сервера
     */
    public ResponseSender(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Метод выполняет отправку сообщений
     * @param string          строка, преобразуется в байтовый массив
     * @param receiverAddress адрес получателя
     * @param receiverPort    порт получателя
     */
    public void send(String string, InetAddress receiverAddress, int receiverPort) throws IOException {

        byte[] byteUDP = new byte[4096];

        DatagramPacket dp = new DatagramPacket(byteUDP, byteUDP.length, receiverAddress, receiverPort);
        // преобразование строки в байтовый массив
        byte[] byteArr = string.getBytes(StandardCharsets.UTF_8);

        if (byteArr.length > 4096) {
            rootLogger.warn("Размер пакета превышен");
            return;
        } else {
            // копирование данных из одного байтового массива в другой
            System.arraycopy(byteArr, 0, byteUDP, 0, byteArr.length);
        }
        // отправка пакета dp
        serverSocket.send(dp);
    }
}
