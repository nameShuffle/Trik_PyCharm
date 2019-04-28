package Network;

public class Command {
    private String type;
    private String contents;
    private String full;
    private int length;

    public Command(String type, String contents) {
        this.type = type;
        this.contents = contents;

        if (!this.contents.equals("")) {
            this.full = this.type + ":" + this.contents;
        }
        else {
            this.full = this.type;
        }

        this.length = this.full.length();

        this.full = this.length + ":" + this.full;
    }

    public String getType() {
        return this.type;
    }

    public String getContents() {
        return this.contents;
    }

    public String getFull() {
        return this.full;
    }

    public int getLength() {
        return length;
    }
}
