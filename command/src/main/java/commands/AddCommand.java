package commands;

import collection.CollectionManager;
import collection.HumanBeing;
import commands.abstr.Command;
import commands.abstr.InvocationStatus;
import exceptions.CannotExecuteCommandException;
import file.HumanBeingReader;

import java.io.PrintStream;
import java.util.ArrayList;

public class AddCommand extends Command {

    private CollectionManager collectionManager;

    private HumanBeingReader humanBeingReader;

    public AddCommand(CollectionManager collectionManager, HumanBeingReader humanBeingReader) {
        this.collectionManager = collectionManager;
        this.humanBeingReader = humanBeingReader;
    }

    public AddCommand(HumanBeingReader humanBeingReader) {
        super("add");
        this.humanBeingReader = humanBeingReader;
    }

    public AddCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String[] arguments, InvocationStatus invocationEnum, PrintStream printStream) throws CannotExecuteCommandException {
        if (invocationEnum.equals(InvocationStatus.CLIENT)) {
            result = new ArrayList<>();
            if (arguments.length > 0) {
                throw new CannotExecuteCommandException("У этой команды нет аргументов");
            }
            HumanBeing humanBeing = humanBeingReader.read();
            super.result.add(humanBeing);
        } else if (invocationEnum.equals(InvocationStatus.SERVER)) {

            HumanBeing humanBeing = (HumanBeing) this.getResult().get(0);
            collectionManager.add(humanBeing);
        }
    }

    @Override
    public String getDescription() {
        return "добавляет новый элемент в коллекцию";
    }
}
