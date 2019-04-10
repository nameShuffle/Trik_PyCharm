package View;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;

public class View {
    private JPanel myToolWindowContent;
    private JButton RunButton;
    private JButton StopButton;
    private JButton SaveButton;
    private JTable TableOfVariables;

    private Presenter presenter;

    public View(ToolWindow toolWindow) {
        addListenersToButtons();
    }

    public void setPresenter (Presenter presenter) {
        this.presenter = presenter;
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    private void addListenersToButtons(){
        this.SaveButton.addActionListener((ActionEvent e) ->
                presenter.sendFileToRobot()
        );

        this.RunButton.addActionListener((ActionEvent e) ->
                presenter.runProgramOnRobot()
        );

        this.StopButton.addActionListener((ActionEvent e) ->
                presenter.stopExecutingProgramOnRobot()
        );
    }

    public void setTableModelToTable(TableModel model)
    {
        this.TableOfVariables.setModel(model);
    }

}
