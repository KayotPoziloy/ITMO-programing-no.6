import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Точка входа для клиентского приложения
 */
public class MainClient {

    private static final Logger rootLogger = LogManager.getRootLogger();

    /**
     * Точка входа в программу
     * @param args порт для присоединения клиента
     */
    public static void main(String[] args) {

        if (args.length != 0) {
            // Создание объекта приложения с указанным портом из аргумента командной строки
            Application application = new Application(Integer.parseInt(args[0]));
            try {
                application.start();
            } catch (NumberFormatException ex) {
                rootLogger.warn("Значение порта должно быть целочисленным.\n Введенное значение: " + args[0]);
            }
        } else {
            // Если аргументы командной строки отсутствуют, запрашиваем порт у пользователя
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите порт: ");
            String portInput = scanner.nextLine();

            try {
                int port = Integer.parseInt(portInput);
                Application application = new Application(port);
                application.start();
            } catch (NumberFormatException ex) {
                rootLogger.error("Значение порта должно быть целочисленным.\n Введенное значение: " + portInput);
            }
        }
    }
}