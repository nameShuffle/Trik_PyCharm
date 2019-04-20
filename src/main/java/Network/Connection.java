package Network;

import java.net.*;
import java.io.*;

public class Connection {

    private String address;
    private int port;

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream out = null;

    /** Class provides simple instruments for communication with TRIK.
     */
    public Connection() {
    }

    /**
     * @param address Address of a TRIK server.
     * @param port Port that the TRIK server hears.
     */
    public Connection(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Connect with a TRIK.
     */
    private void connect() throws IOException {
        socket = new Socket(address, port);
        System.out.println("Connected to " + address + " on port " + port);

        out = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }

    /**
     * disconnect from a TRIK.
     */
    private void disconnect() throws IOException {
        input.close();
        out.close();
        socket.close();
    }

    /**
     * This method
     * @param command Command that should be sent.
     * @return Received string.
     */
    private String sendAndReceive(String command)  throws IOException{
        String receivedString = "empty";

        out.writeBytes(command);
        // receivedString = input.readUTF();

        return receivedString;
    }


    /**
     * Send a command to robot.
     * There are 5 types of command:
     * - file:<file name>:<file contents> --- save given contents to a file with given name in current directory.
     * - run:<file name> --- execute a file with given name.
     * - stop --- stop current script execution and a robot.
     * - keepalive --- do nothing, used to check the availability of connection.
     * @param commandType One of the commands defined above.
     * @param commandContents Contents of a command.
     */
    public void sendCommand(String commandType, String commandContents) throws IOException {
        try {
            connect();
        }
        catch (IOException ioEx) {

            return;
        }

        String command;
        if (commandContents.equals("")) {
            command = commandType;
        }
        else {
            command = commandType + ":" + commandContents;
        }

        command = command.length() + ":" + command;
        System.out.println("Command: " + command);

        try {
           String answer =  sendAndReceive(command);

           System.out.println(answer);
        }
        finally {
            disconnect();
        }
    }

    /**
     * Setter and getter for address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Setter and getter for port.
     */

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }
}