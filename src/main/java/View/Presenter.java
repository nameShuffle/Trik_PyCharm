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

    public void sendFileToRobot() {
        ///TODO
        Helper.FileManager fileManager = new Helper.FileManager();

        String fileContents;

        Connection connection = new Connection(model.getAddress(), model.getPort());

    }

    public void runProgramOnRobot() {

    }

    public void stopExecutingProgramOnRobot() {

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
