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
     * @param serverSocket
     */
    public RequestReader(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;
        dp = new DatagramPacket(byteUPD, byteUPD.length);
    }
    public void readCommand() throws IOException, ClassNotFoundException {
        serverSocket.receive(dp);
        byteUPD = dp.getData();

        senderAddress = dp.getAddress();
        senderPort = dp.getPort();

        String string = new String(byteUPD);
        string = string.replace("\0", "");
        byte[] byteArr = string.getBytes(StandardCharsets.UTF_8);

        var byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(byteArr));
        var objectInputStream = new ObjectInputStream(byteArrayInputStream);

        rootLogger.info("Получен пакет с командой от " + senderAddress.getHostAddress() + " " + senderPort);

        commandContainer = (CommandContainer) objectInputStream.readObject();
        rootLogger.info("Контейнер с командой получен");
    }

    public CommandContainer getCommandContainer() {
        return commandContainer;
    }

    public InetAddress getSenderAddress() {
        return senderAddress;
    }

    public int getSenderPort() {
        return senderPort;
    }
}