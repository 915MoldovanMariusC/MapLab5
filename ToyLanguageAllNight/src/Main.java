import Controller.Controller;
import Repository.Repository;
import View.View;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Log file path ");
        String file = scanner.nextLine();
        Repository repo = new Repository(file);
        Controller controller = new Controller(repo);
        View view = new View(controller);
        view.run();
    }
}