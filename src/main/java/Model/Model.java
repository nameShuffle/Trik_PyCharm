package Model;

public class Model {
    private String address
            = "127.0.0.1";
    private int port
            = 8888;

    private String pathToFile
            = "";

    private String currentFile
            = "default_file.py";

    public Model() {
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public String getCurrentFile() {
        return this.currentFile;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }

    public String getPathToFile() {
        return pathToFile;
    }
}
