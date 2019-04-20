package Network;

import java.net.*;
import java.io.*;

public class Connection {

    private String address;
    private int port;

    private Socket socket;
    protected DataInputStream input;
    protected DataOutputStream output;

    /**
     * @param address Address of the server.
     * @param port Port that the server hears.
     */
    public Connection(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Connects with the server.
     */
    public void connect() throws IOException {
        socket = new Socket(address, port);
        System.out.println("Connected to " + address + " on port " + port);

        output = new DataOutputStream(socket.getOutputStream());
        input = new DataInputStream(socket.getInputStream());
    }

    /**
     * Disconnect from the server.
     */
    public void disconnect() throws IOException {
        input.close();
        output.close();
        socket.close();
    }

    /**
     * @return input stream.
     */
    public DataInputStream getInput() {
        return input;
    }

    /**
     * @return output stream.
     */
    public DataOutputStream getOutput() {
        return output;
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