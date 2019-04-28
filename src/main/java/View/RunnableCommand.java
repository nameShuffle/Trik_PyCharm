package View;

import Async.AsyncExecutor;
import Network.Command;
import Network.RobotConnection;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Provides tasks for the commands.
 */
public class RunnableCommand implements Runnable{
    private Command command;
    private AsyncExecutor asyncExecutor;
    private Model model;

    public RunnableCommand(Command command, AsyncExecutor asyncExecutor, Model model) {
        this.command = command;
        this.asyncExecutor = asyncExecutor;
        this.model = model;
    }

    /**
     * Provides differents tasks according to the commands.
     */
    public void run() {
        sendCommandTask();
    }


    /**
     * Task for the file command.
     */
    private void sendCommandTask() {
        RobotConnection robotConnection = new RobotConnection(model.getAddress(), model.getPort());

        try {
            robotConnection.connect();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        DataInputStream input = robotConnection.getInput();

        robotConnection.sendCommand(this.command);
    }
}
