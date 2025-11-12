import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StreamingService s = new StreamingService();
       s.start();
        s.createMovies();
        s.running();
        //s.mainMenu();
        //s.showMovieByCategory("Crime");
        /*Scanner scanner = new Scanner(System.in);

        ArrayList<User> users;

*/

    }
}