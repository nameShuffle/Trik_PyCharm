package Network;

import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
     * - direct:<command> --- execute given script without saving it to a file.
     * - keepalive --- do nothing, used to check the availability of connection.
     * @param command requested command.
     */
    public void sendCommand(Command command){
        try {
            output.writeBytes(command.getFull());

            System.out.println(command.getFull());
        }
        catch (IOException ioEx)
        {
            ioEx.printStackTrace();

            System.out.println(ioEx.toString());
        }
    }

    public void connectAndSend(Command command) throws IOException{
        connect();

        sendCommand(command);
    }
}
