package commands;

import collection.CollectionManager;
import commands.abstr.Command;
import commands.abstr.CommandContainer;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;
import file.HumanBeingReader;
import io.User;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;

/**
 * Класс, через который осуществляется исполнение команд. Хранит коллекции всех существующих команд.
 */
public class CommandManager {

    /**
     * Коллекция команд для клиента.
     */
    private HashMap<String, Command> clientCommands;

    /**
     * Коллекция команд для сервера.
     */
    private HashMap<String, Command> serverCommands;

    /**
     * Хранит ссылку менеджер коллекции.
     *
     * @see CollectionManager
     */
    private CollectionManager collectionManager;

    /**
     * Хранит ссылку на объект, осуществляющий чтение полей из указанного в userIO потока ввода.
     *
     * @see HumanBeingReader
     */
    private HumanBeingReader humanBeingReader;

    /**
     * Хранит объект класса ExecuteScript.Script.
     *
     * @see ExecuteScriptCommand
     */
    ExecuteScriptCommand.Script script;

    /**
     * Хранит объект, предназначенный для чтения данных из указанного потока ввода.
     */
    private User user;

    /**
     * Контейнер с командой.
     */
    private CommandContainer lastCommandContainer;

    /**
     * Файл, куда сохранять коллекцию в случае завершения работы сервера.
     */
    private String inputFile;

    /**
     * Конструктор класса. Внутри вызывается метод putCommands, добавляющий команды в коллекции команд, создается новый объект класса ExecuteScript.Script.
     *
     * @param user читает данные из указанного потока.
     */
    public CommandManager(User user) { //для клиента
        this.clientCommands = new HashMap<>();
        this.user = user;
        this.humanBeingReader = new HumanBeingReader(user);

        this.script = new ExecuteScriptCommand.Script();
        this.putClientCommands();
        System.out.println("Элементы коллекции для клиента были загружены.");
    }

    /**
     * Конструктор класса, предназначенный для исполнения скрипта на клиенте.
     * @param user читает данные из указанного потока.
     * @param humanBeingReader осуществляет чтение полей, валидацию и преобразование в объект класса Dragon.
     * @param script скрипт, хранит пути до файлов, из которых считывать команды.
     */
    public CommandManager(User user, HumanBeingReader humanBeingReader, ExecuteScriptCommand.Script script) {
        this.clientCommands = new HashMap<>();

        this.user = user;
        this.humanBeingReader = humanBeingReader;
        this.script = script;

        this.putClientCommands();
    }

    /**
     * Конструктор класса, предназначенный для сервера.
     *
     * @param collectionManager менеджер коллекции.
     * @param inputFile файл, куда следует сохранять коллекцию по завершении работы сервера.
     */
    public CommandManager(CollectionManager collectionManager, String inputFile) {
        this.serverCommands = new HashMap<>();

        this.collectionManager = collectionManager;

        this.inputFile = inputFile;

        script = new ExecuteScriptCommand.Script();

        this.putServerCommands();
        System.out.println("Элементы коллекции для сервера были загружены");
    }

    /**
     * Конструктор класса, предназначенный для сервера.
     * @param collectionManager менеджер коллекции.
     */
    public CommandManager(CollectionManager collectionManager) {
        this.serverCommands = new HashMap<>();

        this.collectionManager = collectionManager;

        this.putServerCommands();
    }

    /**
     * Метод, добавляющий клиентские команды в соответствующую коллекции.
     */
    private void putClientCommands() {
        clientCommands.put("info", new InfoCommand());
        clientCommands.put("show", new ShowCommand());
        clientCommands.put("clear", new ClearCommand());
        clientCommands.put("exit", new ExitCommand());
        clientCommands.put("help", new HelpCommand(clientCommands));

        clientCommands.put("execute_script", new ExecuteScriptCommand(user, humanBeingReader, script));
    }

    /**
     * Метод, добавляющий серверные команды в соответствующую коллекцию.
     */
    private void putServerCommands() {
        serverCommands.put("info", new InfoCommand(collectionManager));//y
        serverCommands.put("show", new ShowCommand(collectionManager));//y
        serverCommands.put("clear", new ClearCommand(collectionManager));//y
        serverCommands.put("save", new SaveCommand(collectionManager));//y
        serverCommands.put("help", new HelpCommand(serverCommands));//y

        serverCommands.put("execute_script", new ExecuteScriptCommand(collectionManager));
    }

    /**
     * Метод, который определяет из полученной строки команду со стороны клиента, исполняет ее и передает ей необходимые аргументы.
     * Если команда не распознана, то в заданный поток вывода выводится соответствующее сообщение.
     *
     * @param firstCommandLine Первая строка команды, где хранится само ее название и переданные на этой же строке аргументы.
     * @param printStream поток вывода, куда следует записывать информацию  при исполнеии команды.
     *
     * @return boolean: true - команда исполнена, false - команда не исполнена.
     */
    public boolean executeClient(String firstCommandLine, PrintStream printStream) {

        String[] words = firstCommandLine.trim().split("\\s+");
        String[] arguments = Arrays.copyOfRange(words, 1, words.length);

        try {
            if (clientCommands.containsKey(words[0].toLowerCase(Locale.ROOT))) {
                Command command;
                command = clientCommands.get(words[0].toLowerCase(Locale.ROOT));

                command.execute(arguments, InvocationStatus.CLIENT, printStream);
                lastCommandContainer = new CommandContainer(command.getName(), command.getResult());
                return true;
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (CannotExecuteCommandException ex) {
            printStream.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Метод, который определяет из полученной строки команду со стороны сервера, исполняет ее и передает ей необходимые аргументы.
     *
     * @param firstCommandLine имя команды, аргументы.
     * @param result данные, необходимые для исполнения серверной части команды, полученные от клиента.
     * @param printStream поток вывода, куда следует записывать информацию  при исполнеии команды.
     *
     * @return boolean: true - команда исполнена, false - команда не исполнена.
     */
    public boolean executeServer(String firstCommandLine, ArrayList<Object> result, PrintStream printStream) {

        String[] words = firstCommandLine.trim().split("\\s+");
        String[] arguments = Arrays.copyOfRange(words, 1, words.length);
        try {
            if (serverCommands.containsKey(words[0].toLowerCase(Locale.ROOT))) {
                Command command;
                command = serverCommands.get(words[0].toLowerCase(Locale.ROOT));

                command.setResult(result);
                command.execute(arguments, InvocationStatus.SERVER, printStream); //throws CannotExecuteCommandException
                return true;
            }
        } catch (NullPointerException ex) {
            System.out.println("Команда " + words[0] + " не распознана, для получения справки введите команду help");
            ex.printStackTrace();
        } catch (CannotExecuteCommandException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    /**
     * Метод, возвращающий созданный контейнер с командой.
     * @return CommandContainer - контейнер с командой.
     */
    public CommandContainer getLastCommandContainer() {
        return lastCommandContainer;
    }
}