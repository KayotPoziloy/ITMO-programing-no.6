import commands.CommandManager;
import commands.abstr.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintStream;

public class CommandProcessor {

    private final CommandManager commandManager;

    /**
     * Корневой логгер для записи логов
     */
    private static final Logger rootLogger = LogManager.getRootLogger();

    public CommandProcessor(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void executeCommand(CommandContainer command, PrintStream printStream) {

        if (commandManager.executeServer(command.getName(), command.getResult(), printStream)) {
            rootLogger.info("Была исполнена команда " + command.getName());
        } else {
            rootLogger.info("Не была исполнена команда " + command.getName());
        }
    }
}