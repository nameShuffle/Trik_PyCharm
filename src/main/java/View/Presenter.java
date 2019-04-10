package View;

import Network.Connection;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;

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
     * Creates a command for sending current opened file to robot.
     */
    public void sendFileToRobot() {
        Project[] projects = ProjectManager.getInstance().getOpenProjects();

        Document currentDocument = FileEditorManager.getInstance(projects[0]).getSelectedTextEditor().getDocument();
        VirtualFile currentFile = FileDocumentManager.getInstance().getFile(currentDocument);

        String fileContent = currentDocument.getText();
        String fileName = currentFile.getName();

        String command = fileName + ':' + fileContent;

        Connection connection = new Connection(model.getAddress(), model.getPort());
        connection.doCommand("file", command);
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
