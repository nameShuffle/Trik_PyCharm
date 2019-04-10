package View;

/**
 * The class for variable with two fields:
 *  - name of variable
 *  - current value of variable
 */
public class Variable {
    private String name;
    private String value;

    public Variable(String name, String value) {
        this.setName(name);
        this.setValue(value);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
