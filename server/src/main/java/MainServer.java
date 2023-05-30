import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


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

        if (args.length > 0) {
            if (!args[0].equals("")) {
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
                } catch (Exception e) {
                    rootLogger.warn("По указанному адресу нет подходящего файла " + args[0]);
                }
            }
        } else {
            String envVariable = System.getenv("Lab5");
            try {
                application.start(envVariable);
            } catch (Exception e) {
                rootLogger.warn("По указанному адресу нет подходящего файла " + envVariable);
            }
        }
    }
}