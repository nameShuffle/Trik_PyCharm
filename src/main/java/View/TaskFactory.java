package View;

import Async.AsyncExecutor;
import Network.Command;
import Network.RobotConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Provides tasks for the commands.
 */
public class TaskFactory implements Runnable{
    private Command command;
    private AsyncExecutor asyncExecutor;
    private Model model;

    public TaskFactory(Command command, AsyncExecutor asyncExecutor, Model model) {
        this.command = command;
        this.asyncExecutor = asyncExecutor;
        this.model = model;
    }

    public void run() {

    }

    private void sendFileToRobotTask() {
        RobotConnection robotConnection = new RobotConnection(model.getAddress(), model.getPort());

        try {
            robotConnection.connect();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        DataOutputStream output = robotConnection.getOutput();
        DataInputStream input = robotConnection.getInput();
    }
}
