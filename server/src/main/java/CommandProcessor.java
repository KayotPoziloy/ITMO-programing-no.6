import commands.CommandManager;
import commands.abstr.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;

/**
 * Передает команду в CommandManager для выполнения команды
 */
public class CommandProcessor {

    private final CommandManager commandManager;

    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();

    /**
     * Конструктор класса
     * @param commandManager менеджер команд
     */
    public CommandProcessor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду и записывает результат выполнения в указанный PrintStream
     * @param command     контейнер команды
     * @param printStream вывод результатов команды
     */
    public void executeCommand(CommandContainer command, PrintStream printStream) {

        if (commandManager.executeServer(command.getName(), command.getResult(), printStream)) {
            rootLogger.info("Была исполнена команда " + command.getName());
        } else {
            rootLogger.info("Не была исполнена команда " + command.getName());
        }
    }
}