package View;

import com.intellij.util.containers.hash.HashSet;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Set;

/**
 * TableModel for Table of variables.
 */
public class TableOfVariablesModel implements TableModel {

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();

    private List<Variable> currentVariables;

    public TableOfVariablesModel() { }


    public TableOfVariablesModel(List<Variable> variables) {
        this.currentVariables = variables;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 2;
    }

    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Name";
            case 1:
                return "Value";
            case 2:
                return "Описание";
        }
        return "";
    }

    public int getRowCount() {
        return currentVariables.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Variable bean = currentVariables.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return bean.getName();
            case 1:
                return bean.getValue();
        }
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

    public void addVariable(Variable variable)
    {
        this.currentVariables.add(variable);
    }
}
