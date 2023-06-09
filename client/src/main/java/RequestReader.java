import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Класс для чтения запросов от сервера
 */
public class RequestReader {

    private final DatagramChannel clientChannel;

    private static final Logger rootLogger = LogManager.getRootLogger();

    /**
     * Конструктор класса
     * @param clientChannel канал клиента для чтения запросов
     */
    public RequestReader(DatagramChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    /**
     * Читает буфер с данными, полученными от сервера
     * @return ByteBuffer с данными от сервера.
     * @throws IOException          если возникает ошибка при чтении из канала
     * @throws InterruptedException если потом был прерван во время ожидания
     */
    public ByteBuffer receiveBuffer() throws IOException, InterruptedException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        int waitingTime = 0;

        while (waitingTime < 10) {
            byteBuffer.clear();
            SocketAddress from = clientChannel.receive(byteBuffer);

            if (from != null) {
                byteBuffer.flip();
                return byteBuffer;
            }
            Thread.sleep(500);
            waitingTime++;
        }
        rootLogger.error("Сервер не отвечает. Завершение работы клиента");
        System.exit(0);
        return byteBuffer;
    }
}