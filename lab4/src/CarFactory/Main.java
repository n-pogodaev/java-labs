package CarFactory;

import CarFactory.Controller.Controller;
import CarFactory.Model.Model;
import CarFactory.View.GUI;
import CarFactory.View.View;

public class Main {
    public static void main(String[] args) {
        Model model = new Model(ConfigReader.readConfig());
        View view = new GUI(model);
        Controller controller = new Controller(view, model);
        controller.start();
    }
}
