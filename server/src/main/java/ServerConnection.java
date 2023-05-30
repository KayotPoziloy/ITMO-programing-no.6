import java.net.DatagramSocket;
import java.net.SocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Соединение сервера, создающее DatagramSocket на основе указанного порта
 */
public class ServerConnection {
    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();
    /**
     * Предоставляет сокет сервера для обмена данными
     */
    private DatagramSocket serverSocket;

    /**
     * @param port по этому порту создает DatagramSocket
     * @return если создание сокета прошло успешно, возвращает true иначе false и выход
     */
    public boolean createFromPort(Integer port) {
        try {
            serverSocket = new DatagramSocket(port);
            rootLogger.info("Сервер готов");
            return true;
        } catch (SocketException e) {
            rootLogger.warn("Порт " + port + " занят или не может быть открыт");
            System.exit(-1);
        }
        return false;
    }

    /**
     * @return значение сокета
     */
    public DatagramSocket getServerSocket() {
        return serverSocket;
    }
}