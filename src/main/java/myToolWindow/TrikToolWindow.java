package myToolWindow;

import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class TrikToolWindow {
    private JPanel myToolWindowContent;
    private JButton SaveButton;
    private JButton RunButton;
    private JTable table1;

    public TrikToolWindow(ToolWindow toolWindow) {

    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}