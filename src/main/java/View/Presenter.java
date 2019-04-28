package View;

import Async.AsyncExecutor;
import Network.Command;

import javax.swing.table.TableModel;
import java.util.ArrayList;


public class Presenter {

    private View view;
    private Model model;
    private TableModel tableModel;

    private final int numberOfThreads = 10;
    private AsyncExecutor asyncExecutor = new AsyncExecutor(numberOfThreads);

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Sends command to a robot asynchronously.
     */
    private void manageCommandAsync(Command command) {
        asyncExecutor.execute(new RunnableCommand(command, asyncExecutor, model));
    }

    /**
     * Creates a command for sending current opened file to a robot.
     */
    public void sendFileToRobot() {
        String fileName = model.getCurrentFileName();
        String fileContents = model.getCurrentFileContents();

        String commandContents = fileName + ":" + fileContents;

        manageCommandAsync(new Command("file", commandContents));
    }

    /**
     * Runs opened file on a robot.
     */
    public void runProgramOnRobot() {
        manageCommandAsync(new Command("direct", model.getCurrentFileName()));
    }

    /**
     * Stops a script execution on a robot.
     */
    public void stopExecutingProgramOnRobot() {
        manageCommandAsync(new Command("stop", ""));
    }

    public void initializeTable()
    {
        ArrayList<Variable> variables = new ArrayList<Variable>();

        for (int i = 0; i < 10; i++) {
            variables.add(new Variable("Имя " + i, "Значение " + i));
        }

        this.tableModel = new TableOfVariablesModel(variables);

        this.view.setTableModelToTable(this.tableModel);
    }

    public void addVariableToTable(Variable variable) {
        ((TableOfVariablesModel) this.tableModel).addVariable(variable);
    }

    public void addDataToTable() {
        //создаем новую переменную или новый список переменных, добавляем в модель
    }

    public void changeDataInTable() {
        //меняем значения уже имеющихся в таблице переменных
    }

    public void setAddress(String address) {
        model.setAddress(address);
    }

    public void setPort(String port) {
        model.setPort(Integer.parseInt(port));
    }
}
