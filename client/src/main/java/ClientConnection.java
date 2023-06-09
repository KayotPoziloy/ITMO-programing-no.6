import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.DatagramChannel;

/**
 * Класс отвечающий за установку соединения клиента с сервером
 */
public class ClientConnection {

    private static final Logger rootLogger = LogManager.getRootLogger();
    private DatagramChannel clientChannel;

    /**
     * Метод устанавливает соединение клиента с заданным сервером
     * @param inetServerAddress адрес и порт сервера
     * @throws IOException если возникает ошибка ввода-вывода при открытии канала или установке соединения
     */
    public void connect(InetSocketAddress inetServerAddress) throws IOException {
        try {
            // Открываем канал DatagramChannel
            clientChannel = DatagramChannel.open();

            // Устанавливаем неблокирующий режим для канала
            clientChannel.configureBlocking(false);

            // Устанавливаем соединение с заданным адресом сервера
            clientChannel.connect(inetServerAddress);
        } catch (IllegalArgumentException ex) {
            rootLogger.error("Указан недопустимый порт: " + inetServerAddress.getPort());
        } catch (SocketException ex) {
            rootLogger.error("Установка соденинения не удалась\n" + ex);
        }
    }

    /**
     * @return DatagramChannel канал клиента
     */
    public DatagramChannel getClientChannel() {
        return clientChannel;
    }
}