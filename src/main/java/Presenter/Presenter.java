package Presenter;

import Presenter.Helper.FileManager;
import Model.Model;
import Network.Connection;

import javax.swing.text.View;


public class Presenter {

    private View view;
    private Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void sendFileToRobot() {
        ///TODO
        Presenter.Helper.FileManager fileManager = new Presenter.Helper.FileManager();

        String fileContents =

        Connection connection = new Connection(model.getAddress(), model.getPort());

    }
}
