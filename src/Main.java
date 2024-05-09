import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        Controller controller = Controller.getInstance();
        controller.addDefence(new MainController());
        controller.addDefence(new Helicopter());
        controller.addDefence(new Tank());
        controller.addDefence(new Submarine());
    }
}
