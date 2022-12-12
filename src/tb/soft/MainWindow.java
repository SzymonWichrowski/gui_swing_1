package tb.soft;

import javax.swing.*;

/**
 * klasa główna zawierająca metodę statyczną main
 */
public class MainWindow {
    public static void main(String[] args) {

        Window window = new Window();       //wywołanie konstruktora klasy Window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //close operation
        window.setVisible(true);
    }
}
