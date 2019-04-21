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
     * Send a command to a robot.
     * There are 5 types of command:
     * - file:<file name>:<file contents> --- save given contents to a file with given name in current directory.
     * - run:<file name> --- execute a file with given name.
     * - stop --- stop current script execution and a robot.
     * - keepalive --- do nothing, used to check the availability of connection.
     * @param commandType One of the commands defined above.
     * @param commandContents Contents of a command.
     */
    public void sendCommand(String commandType, String commandContents){
        String command = commandType;

        if (!commandContents.equals("")) {
            command += ":" + commandContents;
        }

        command = command.length() + ":" + command;
        System.out.println("Command: " + command);

        try {
            output.writeUTF(command);
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();

            System.out.println(ioEx.toString());
        }
    }
}
