import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.BindException;


/**
 * Точка запуска программы сервера
 */
public class MainServer {
    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();

    /**
     * Метод, запускающий серверное приложение
     *
     * @param args Аргументы командной строки
     */
    public static void main(String[] args) {
        Application application = new Application();

        // проверка на наличие аргументов командной строки
        if (args.length > 0) {
            if (!args[0].equals("")) {
                // Добавляем Shutdown Hook для сохранения коллекции при завершении работы программы
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    rootLogger.info("Сохранение коллекции в файл");
                    application.getCollectionManager().saveCollection();
                    rootLogger.info("Коллекция была сохранена " + args[0]);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        rootLogger.error("Ошибка с потоками: " + e);
                    }
                    rootLogger.info("Завершение работы сервера");
                }
                ));
                try {
                    application.start(args[0]);
                } catch (BindException e) {
                    rootLogger.warn("данный порт занят " + e);
                } catch (Exception e) {
                    rootLogger.warn("По указанному адресу нет подходящего файла " + args[0]);
                }
            }
        } else {
            // Если аргументы командной строки отсутствуют, получаем путь к файлу из переменной окружения Lab5
            String envVariable = System.getenv("Lab5");
            try {
                application.start(envVariable);
            } catch (BindException e) {
                rootLogger.warn("данный порт занят " + e);
            } catch (IOException | ParserConfigurationException e) {
                rootLogger.warn(e + " По указанному адресу нет подходящего файла " + envVariable);
            } catch (Exception e) {
                rootLogger.error("Неизвестная ошибка" + e);
            }
        }
    }
}