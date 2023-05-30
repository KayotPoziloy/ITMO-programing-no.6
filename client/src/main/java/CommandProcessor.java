
import commands.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProcessor {

    private static final Logger rootLogger = LogManager.getRootLogger();

    private final CommandManager commandcommandManager;

    public CommandProcessor(CommandManager commandManager) {
        this.commandcommandManager = commandManager;
    }

    public boolean executeCommand(String firstCommandLine) {

        if (!commandcommandManager.executeClient(firstCommandLine, System.out)) {
            rootLogger.warn("Команда не была исполнена");
            return false;
        } else {
            return !commandcommandManager.getLastCommandContainer().getName().equals("help");
        }
    }
}