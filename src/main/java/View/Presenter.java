package View;

import Async.AsyncExecutor;
import Network.Command;
import Network.RobotConnection;
import View.View.StopRunner;
import org.apache.commons.io.IOUtils;

import javax.swing.table.TableModel;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Presenter {

    private View view;
    private Model model;
    private TableModel tableModel;

    private final int numberOfThreads = 10;
    private AsyncExecutor asyncExecutor = new AsyncExecutor(numberOfThreads);

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Sends command to a robot asynchronously.
     */
    private void manageCommandAsync(Command command) {
        if (command.getType().equals("run")) {
            view.lockRunButtonAndTextFields();
        }

        asyncExecutor.execute(new RunnableCommand(command));
    }

    /**
     * Creates a command for sending current opened file to a robot.
     */
    public void sendFileToRobot() {
        String fileName = model.getCurrentFileName();
        String fileContents = model.getCurrentFileContents();

        String commandContents = fileName + ":" + fileContents;

        manageCommandAsync(new Command("file", commandContents));
    }

    /**
     * Runs opened file on a robot.
     */
    public void runProgramOnRobot() {
        String fileName = model.getCurrentFileName();
        String fileContents = model.getCurrentFileContents();

        String commandContents = fileName + ":" + fileContents;

        manageCommandAsync(new Command("run", commandContents));
    }

    /**
     * Stops a script execution on a robot.
     */
    public void stopExecutingProgramOnRobot() {
        manageCommandAsync(new Command("stop", ""));
    }

    public void initializeTable()
    {
        ArrayList<Variable> variables = new ArrayList<Variable>();

        for (int i = 0; i < 10; i++) {
            variables.add(new Variable("Имя " + i, "Значение " + i));
        }

        this.tableModel = new TableOfVariablesModel(variables);

        this.view.setTableModelToTable(this.tableModel);
    }

    public void addVariableToTable(Variable variable) {
        ((TableOfVariablesModel) this.tableModel).addVariable(variable);
    }

    public void addDataToTable() {
        //создаем новую переменную или новый список переменных, добавляем в модель
    }

    public void changeDataInTable() {
        //меняем значения уже имеющихся в таблице переменных
    }

    public void setAddress(String address) {
        model.setAddress(address);
    }

    public void setPort(String port) {
        model.setPort(Integer.parseInt(port));
    }

    /**
     * Provides tasks for the commands.
     */
    public class RunnableCommand implements Runnable{
        private Command command;

        public RunnableCommand(Command command) {
            this.command = command;
        }

        /**
         * Provides differents tasks according to the commands.
         */
        public void run() {
            switch (this.command.getType()) {
                case "file":
                case "stop":
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

            try {
                connect(address, port, new Command("file", command.getContents()));

                connect(address, port, new Command("run", command.getContents().split(":")[0]));
            }
            catch (IOException ioEx) {
                ioEx.printStackTrace();
                System.out.println(ioEx.toString());
            }
            finally {
                (view.new StopRunner()).execute();

            }
        }

        /**
         * Task for the file command.
         */
        private void commonCommandTask() {
            try {
                connect(model.getAddress(), model.getPort(), this.command);
            }
            catch (IOException ioEx) {
                ioEx.printStackTrace();
                System.out.println(ioEx.toString());
            }
            finally {
                (view.new StopRunner()).execute();
            }

        }

        /**
         * Connects and sends command to a robot.
         * @return A robot request.
         */
        private String connect(String address, int port, Command command) throws IOException {
            RobotConnection fileCommandConnection = new RobotConnection(address, port);

            fileCommandConnection.connectAndSend(command);

            DataInputStream fileInput = fileCommandConnection.getInput();
            String result = IOUtils.toString(fileInput, StandardCharsets.UTF_8);

            System.out.println(result);

            return result;
        }
    }
}
