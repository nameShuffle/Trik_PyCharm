package View;

import Async.AsyncExecutor;
import Network.RobotConnection;

import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.ArrayList;


public class Presenter {

    private View view;
    private Model model;
    private TableModel tableModel;

    private final int numberOfThreads = 10;
    private AsyncExecutor asyncExecutor = new AsyncExecutor(numberOfThreads);

    /**
     * This runnable declares task for file command.
     */
    private Runnable runnableFileTask = () -> {
        RobotConnection robotConnection = new RobotConnection(model.getAddress(), model.getPort());

        try {
            robotConnection.connect();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        //TODO: Add sending and disconnecting.
    };

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Sends command to a robot.
     * @param commandType Type of the command.
     * @param command Command data.
     */
    private void manageCommandAsync(String commandType, String command) {
        RobotConnection connection = new RobotConnection(model.getAddress(), model.getPort());

        connection.sendCommand(commandType, command);
    }

    /**
     * Creates a command for sending current opened file to a robot.
     */
    public void sendFileToRobot() {
        String fileName = model.getCurrentFileName();
        String fileContents = model.getCurrentFileContents();

        String command = fileName + ":" + fileContents;

        manageCommandAsync("file", command);
    }

    /**
     * Runs opened file on a robot.
     */
    public void runProgramOnRobot() {
        String command = model.getCurrentFileName();

        manageCommandAsync("run", command);
    }

    /**
     * Stops a script execution on a robot.
     */
    public void stopExecutingProgramOnRobot() {
        manageCommandAsync("stop", "");
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
