package App;

import App.Controller.Menu;

import java.awt.*;

public class AppMain {
    public static void main(String[] args) {
        Menu menu = new Menu();
        EventQueue.invokeLater(menu::start);
    }
}
