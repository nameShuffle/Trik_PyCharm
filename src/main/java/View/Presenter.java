package View;

import Network.Connection;

import javax.swing.table.TableModel;
import java.util.ArrayList;


public class Presenter {

    private View view;
    private Model model;
    private TableModel tableModel;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Sends command to a robot.
     * @param commandType Type of the command.
     * @param command Command data.
     */
    private void sendCommand(String commandType, String command) {
        Connection connection = new Connection(model.getAddress(), model.getPort());

        connection.sendCommand(commandType, command);
    }

    /**
     * Creates a command for sending current opened file to a robot.
     */
    public void sendFileToRobot() {
        String fileName = model.getCurrentFileName();
        String fileContents = model.getCurrentFileContents();

        String command = fileName + ":" + fileContents;

        sendCommand("file", command);
    }

    /**
     * Runs opened file on a robot.
     */
    public void runProgramOnRobot() {
        String command = model.getCurrentFileName();

        sendCommand("run", command);
    }

    /**
     * Stops a script execution on a robot.
     */
    public void stopExecutingProgramOnRobot() {
        sendCommand("stop", "");
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
}
