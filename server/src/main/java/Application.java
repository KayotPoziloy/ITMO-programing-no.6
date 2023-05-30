import collection.CollectionManager;
import collection.HumanBeing;
import commands.CommandManager;
import commands.abstr.CommandContainer;
import file.FileManager;
import io.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    CollectionManager collectionManager;
    FileManager fileManager;
    User user;
    ServerConnection serverConnection;

    CommandManager commandManager;

    private boolean isConnected;
    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();
    Application() {
        collectionManager = new CollectionManager();
        fileManager = new FileManager();
        user = new User();
        rootLogger.info("Конструктор класса Application загружен");
    }

    public void start(String envVariable) throws IOException, ParserConfigurationException {

        try {
            File ioFile = new File(envVariable);
            if (!ioFile.canWrite() || ioFile.isDirectory() || !ioFile.isFile()) throw new IOException();

            HumanBeing[] humanBeings = fileManager.parseToCollection(envVariable);

            for (HumanBeing humanBeing : humanBeings) {
                collectionManager.add(humanBeing);
            }

            this.commandManager = new CommandManager(collectionManager, envVariable);

            rootLogger.printf(Level.INFO, "Элементы коллекций из файла %1$s были загружены.", envVariable);

            serverConnection = new ServerConnection();

            Scanner scanner = new Scanner(System.in);

            do {
                System.out.print("Введите порт: ");
                int port = scanner.nextInt();
                if (port <= 0) {
                    rootLogger.error("Введенный порт невалиден.");
                } else {
                    isConnected = serverConnection.createFromPort(port);
                }
            } while (!isConnected);
            rootLogger.info("Порт установлен.");
        } catch (NoSuchElementException e) {
            rootLogger.error("Аварийное завершение работы");
            System.exit(-1);
        }
        try {
            cycle(commandManager);
        } catch (NoSuchElementException | InterruptedException e) {
            rootLogger.warn(e.getMessage());
            rootLogger.warn("Работа сервера завершена");
        }
    }

    private void cycle(CommandManager commandManager) throws InterruptedException {
        RequestReader requestReader = new RequestReader(serverConnection.getServerSocket());
        ResponseSender responseSender = new ResponseSender(serverConnection.getServerSocket());
        CommandProcessor commandProcessor = new CommandProcessor(commandManager);
        while (isConnected) {
            try {
                requestReader.readCommand();
                CommandContainer command = requestReader.getCommandContainer();

                var byteArrayOutputStream = new ByteArrayOutputStream();
                var printStream = new PrintStream(byteArrayOutputStream);

                commandProcessor.executeCommand(command, printStream);

                Thread.sleep(1000);
                responseSender.send(
                        byteArrayOutputStream.toString(),
                        requestReader.getSenderAddress(),
                        requestReader.getSenderPort()
                );
                rootLogger.info("Пакет был отправлен " + requestReader.getSenderAddress().getHostAddress() + " " + requestReader.getSenderPort());
            } catch (IOException e) {
                rootLogger.warn("Произошла ошибка при чтении " + e.getMessage());
            } catch (ClassNotFoundException e) {
                rootLogger.error("Неизвестная ошибка " + e);
            }
        }

    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
