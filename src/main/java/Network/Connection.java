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
    private void Connect() throws IOException {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to " + address + " on port " + port);

            out = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());
        } catch(IOException ioEx) {
            throw ioEx;
        }
    }

    /**
     * Disconnect from a TRIK.
     */
    private void Disconnect() throws IOException {
        try {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException ioEx) {
            throw ioEx;
        }
    }

    /**
     * This method
     * @param command
     * @return
     * @throws IOException
     */
    private String SendAndReceive(String command)  throws IOException{
        String receivedString;

        try {
            out.writeUTF(command);

            receivedString = input.readUTF();
        }
        catch(IOException ioEx) {
            throw ioEx;
        }

        return receivedString;
    }


    /**
     * Send a command to robot.
     * There are 5 commands:
     * - file:<file name>:<file contents> --- save given contents to a file with given name in current directory.
     * - run:<file name> --- execute a file with given name.
     * - stop --- stop current script execution and a robot.
     * - direct:<command> --- execute given script without saving it to a file.
     * - keepalive --- do nothing, used to check the availability of connection.
     * @param commandType One of the commands defined above.
     * @param commandContents
     */
    public void DoCommand(String commandType, String commandContents) {
        try {
            Connect();
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);

            return;
        }

        String command = commandType + ":" + commandContents;
        command = command.length() + ":" + command;

        try {
           String answer =  SendAndReceive(command);

           System.out.println(answer);
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
            return;
        }

        try {
            Disconnect();
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }

    /**
     * Send a file command to a robot.
     * This command looks like:
     * file:<file name>:<file contents> --- save given contents to a file with given name.
     * @param fileName Name of file that this method sends.
     * @param fileContents Contents of file.
     */
    public void DoFileCommand(String fileName, String fileContents) {

        try {
            Connect();
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);

            return;
        }

        String command = "file:" + fileName + ":" + fileContents;
        command = command.length() + ":" + command;
        System.out.println("Command: " + command);

        try {
            SendAndReceive(command);
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
            return;
        }

        try {
            Disconnect();
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }

    /**
     * Send a run command to a robot.
     * This command looks like:
     * run:<file name> --- execute a file with given name.
     * @param fileName
     */
    public void DoRunCommand(String fileName) {
        try {
            Connect();
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);

            return;
        }

        String command = "run:" + fileName;
        command = command.length() + ":" + command;

        try {
            SendAndReceive(command);
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
            return;
        }

        try {
            Disconnect();
        }
        catch (IOException ioEx) {
            System.out.println(ioEx);
        }
    }
}