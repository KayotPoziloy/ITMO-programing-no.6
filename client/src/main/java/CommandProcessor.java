
import commands.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProcessor {

    private static final Logger rootLogger = LogManager.getRootLogger();

    private final CommandManager commandcommandManager;

    /**
     * Конструктор класса
     * @param commandManager объект CommandManager, который будет использоваться для выполнения команд.
     */
    public CommandProcessor(CommandManager commandManager) {
        this.commandcommandManager = commandManager;
    }

    /**
     * Метод для выполнения команд
     * @param firstCommandLine строка с первой командой, которую нужно выполнить.
     * @return true, если команда выполнена успешно, иначе false.
     */
    public boolean executeCommand(String firstCommandLine) {

        if (!commandcommandManager.executeClient(firstCommandLine, System.out)) {
            rootLogger.warn("Команда не была исполнена");
            return false;
        } else {
            // Проверяем, была ли последняя команда "help"
            // Если "help", то команда не отправляет на сервер
            return !commandcommandManager.getLastCommandContainer().getName().equals("help");
        }
    }
}