package Network;

import java.io.IOException;

/**
 * This class provides methods for sending commands to a trik-robot and receiving answers.
 */
public class RobotConnection extends Connection {

    public RobotConnection(String address, int port) {
        super(address, port);
    }



    /**
     * This method
     * @param command Command that should be sent.
     * @return Received string.
     */
    private String sendAndReceive(String command)  throws IOException{
        String receivedString = "empty";

        output.writeBytes(command);
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
}
