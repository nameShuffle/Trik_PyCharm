package View;

import Async.AsyncExecutor;
import Network.Command;
import Network.RobotConnection;
import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        switch (this.command.getType()) {
            case "stop":
            case "file":
                commonCommandTask();

                break;

            case "run":
                runCommandTask();

                break;
        }
    }


    /**
     * Task for the run command;
     */
    private void runCommandTask() {
        String address = model.getAddress();
        int port = model.getPort();

        RobotConnection fileCommandConnection = new RobotConnection(address, port);
        try {
            fileCommandConnection.connect();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        fileCommandConnection.sendCommand(new Command("file", command.getContents()));

        DataInputStream fileInput = fileCommandConnection.getInput();

        try {
            String result = IOUtils.toString(fileInput, StandardCharsets.UTF_8);

            System.out.println(result);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        RobotConnection runCommandConnection = new RobotConnection(address, port);

        try {
            runCommandConnection.connect();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        runCommandConnection.sendCommand(new Command(command.getType(), command.getContents().split(":")[0]));
        
        DataInputStream runInput = runCommandConnection.getInput();
        try {
            String result = IOUtils.toString(runInput, StandardCharsets.UTF_8);

            System.out.println(result);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());
        }
    }

    /**
     * Task for the file command.
     */
    private void commonCommandTask() {
        RobotConnection robotConnection = new RobotConnection(model.getAddress(), model.getPort());
        try {
            robotConnection.connect();
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());

            return;
        }

        robotConnection.sendCommand(this.command);

        DataInputStream input = robotConnection.getInput();
        try {
            String result = IOUtils.toString(input, StandardCharsets.UTF_8);

            System.out.println(result);
        }
        catch (IOException ioEx) {
            ioEx.printStackTrace();
            System.out.println(ioEx.toString());
        }
    }
}
