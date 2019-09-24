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
    private JTextField portTextField;
    private JTextField addressTextField;

    private Presenter presenter;

    View(ToolWindow toolWindow) {
        addListenersToButtons();
    }

    void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    JPanel getContent() {
        return myToolWindowContent;
    }

    private void addListenersToButtons(){
        this.SaveButton.addActionListener((ActionEvent e) -> {
                    presenter.setAddress(addressTextField.getText());
                    presenter.setPort(portTextField.getText());

                    presenter.sendFileToRobot();
               }
        );

        this.RunButton.addActionListener((ActionEvent e) -> {
                    presenter.setAddress(addressTextField.getText());
                    presenter.setPort(portTextField.getText());

                    presenter.runProgramOnRobot();
                }
        );

        this.StopButton.addActionListener((ActionEvent e) -> {
                    presenter.setAddress(addressTextField.getText());
                    presenter.setPort(portTextField.getText());

                    presenter.stopExecutingProgramOnRobot();
                }
        );
    }

    void setTableModelToTable(TableModel model)
    {
        this.TableOfVariables.setModel(model);
    }

    public void lockRunButtonAndTextFields() {
        RunButton.setEnabled(false);
        SaveButton.setEnabled(false);

        addressTextField.setEditable(false);
        portTextField.setEditable(false);
    }


    /**
     * Class provides instruments to change the run button and the text fields in asynchronous mode.
     */
    public class StopRunner extends SwingWorker<Object, Object> {
        public Object doInBackground() {
            RunButton.setEnabled(true);
            SaveButton.setEnabled(true);

            addressTextField.setEditable(true);
            portTextField.setEditable(true);

            return new Object();
        }
    }
}
