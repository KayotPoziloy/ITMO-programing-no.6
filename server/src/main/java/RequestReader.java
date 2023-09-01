import commands.abstr.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Чтение команд из DatagramSocket
 */
public class RequestReader {
    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();
    /**
     * Сокет сервера
     */
    private final DatagramSocket serverSocket;

    private byte[] byteUPD = new byte[4096];

    private InetAddress senderAddress;
    private int senderPort;

    private final DatagramPacket dp;
    private CommandContainer commandContainer;

    /**
     * Конструктор класса
     * @param serverSocket - сокет сервера
     */
    public RequestReader(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
        dp = new DatagramPacket(byteUPD, byteUPD.length);
    }

    /**
     * Чтение команд из DatagramSocket
     * @throws IOException            ошибка ввода-вывода
     * @throws ClassNotFoundException класс команды не найден
     */
    public void readCommand() throws IOException, ClassNotFoundException {
        serverSocket.receive(dp); // Принимаем пакет данных
        byteUPD = dp.getData();   // Получаем данные из пакета

        senderAddress = dp.getAddress(); // Получаем адрес отправителя
        senderPort = dp.getPort();       // Получаем порт отправителя

        String string = new String(byteUPD);                      // Преобразуем данные в строку
        string = string.replace("\0", "");       // Удаляем нулевые символы
        byte[] byteArr = string.getBytes(StandardCharsets.UTF_8); // Преобразуем строку обратно в байтовый массив

        // Создаем поток для чтения данных из байтового массива
        var byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(byteArr));
        // Создаем поток для чтения объектов из ByteArrayInputStream
        var objectInputStream = new ObjectInputStream(byteArrayInputStream);

        rootLogger.info("Получен пакет с командой от " + senderAddress.getHostAddress() + " " + senderPort);

        // Читаем объект команды из потока
        commandContainer = (CommandContainer) objectInputStream.readObject();
        rootLogger.info("Контейнер с командой получен");
    }

    /**
     * @return контейнер с командой
     */
    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    /**
     * @return адрес отправителя
     */
    public InetAddress getSenderAddress() {
        return senderAddress;
    }

    /**
     * @return Порт отправителя
     */
    public int getSenderPort() {
        return senderPort;
    }
}