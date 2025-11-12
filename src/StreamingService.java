import utils.FileIO;
import utils.TextUI;
import java.util.ArrayList;


public class StreamingService {
    FileIO fileIO = new FileIO();
    TextUI textUI = new TextUI();
    ArrayList<User> users = new ArrayList<>();
    boolean running = true;
    User loggedInUser;

    ArrayList<Movie> movies = new ArrayList<>();

    public void loadUsers(){
    ArrayList<String>data = fileIO.readData("data/users.csv");
    users.clear();
    for (String s: data){
        if (s.contains(",")){
            String[] values = s.split(",");
            users.add(new User(values [0].trim(), values[1].trim()));
        }
    }
    }

    public void createMovies() {

        ArrayList<String> data = fileIO.readData("data/film.csv");
        for (String s : data) {
            try {
                String[] values = s.split(";");
                String title = values[0];
                int yearOfRealise = Integer.parseInt(values[1].trim());
                String[] categories = values[2].trim().split(",");
                String ratingStr = values[3].trim().replace(",", ".");
                double rating = Double.parseDouble(ratingStr); //Double.parseDouble(values[3]);
                movies.add(new Movie(title, yearOfRealise, categories, rating));
            } catch (NumberFormatException e) {
                System.out.println("Fejl i indlæsning af film");
            }
        }
    }

    public void showMenu() {
        textUI.displayMsg("Indtæst nummer af film");
        int counter = 0;
        for (Movie m : movies) {
            counter++;
            System.out.println(counter + ". " + m.toString());
        }
        int userInput = textUI.promptNumeric("");
        Movie m = movies.get(userInput - 1);
        m.playMovie();
    }


    public void start() {
        textUI.displayMsg("***********Velkomme to CHILL***************");
        createUser();
        loadUsers();
        running();
    }

    public boolean running() {
        while (running) {
            textUI.displayMsg("Start menu");
            textUI.displayMsg("1. Log ind");
            textUI.displayMsg("2. Opret bruger");
            textUI.displayMsg("3. Afslyt");

            int option = safeInput();

            switch (option) {
                case 1:
                    login();break;
                case 2:
                    createUser();break;
                case 3:
                    textUI.displayMsg("Tak for besøge!");
                    running = false;break;
                default:
                    textUI.displayMsg("Forket valg, prøv igen");
            }
        }
        return running = false;
    }
    private int safeInput() {
        try {
            return textUI.promptNumeric("Vælg et nummer");
        } catch (NumberFormatException e) {
            textUI.displayMsg("Ugyldig valg, skriv et tal");
            return -1;
        }
    }
    private void login() {
        String userName = textUI.promptText("Indtæst brugenavn");
        String password = textUI.promptText("Indtæst adganskode");
        for (User u : users) {
            if (u.getUsername().equals(userName) && u.getPassword().equals(password)) {
                loggedInUser = u;
                textUI.displayMsg("Du er log in som " + userName);
                mainMenu();
                return;
            }
        }
    }
    private void createUser() {
        String userName = textUI.promptText("Vælg brugenavn");
        String password = textUI.promptText("Vælg adganskode");
        users.add(new User(userName, password));
        saveUsers();
        textUI.displayMsg("Bruger oprettet");
    }
    private void saveUsers() {
        ArrayList<String> usersAsString = new ArrayList<>();
        for(User u: users){
            usersAsString.add(u.toString());
        }
       fileIO.saveData(usersAsString,"data/users.csv", "username, password");

    }

    public void searchMovieByCategory() {
        String input  = textUI.promptText("Skriv kategory-navn(fx drama, romance, war, family, scifi )").toLowerCase();
        textUI.displayMsg("Vis alle" + input + " film");
        int counter = 0;
        for (Movie m : movies) {
            for (String c : m.getCategories()) {
                if (c.trim().equalsIgnoreCase(input)) {
                    counter++;
                    System.out.println(counter + ". " + m.toString());
                    break;
                }
            }
        }
        if( counter > 0){
            int userInput = textUI.promptNumeric("Valg en film");
            Movie m = movies.get(userInput - 1);
            m.playMovie();
        }else{
            textUI.displayMsg("Vi fandt ingen film");
        }

    }

    public void mainMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            textUI.displayMsg("Hoved menu");
            textUI.displayMsg("1. Trending nu");
            textUI.displayMsg("2. Søg film");
            textUI.displayMsg("3. Søg film efter kategory");
            textUI.displayMsg("4. Mine gemte film");
            textUI.displayMsg("5. Se sete film");
            textUI.displayMsg("6. Log ud");

            int option = safeInput();

            switch (option) {
                case 1:
                    trendingMovie();break;
                case 2:
                    searchMovie();break;
                case 3:
                    searchMovieByCategory(); break;
                case 4:
                    showWatched();break;
                case 5:
                    showWatchLater();break;
                case 6:
                    if(loggedInUser == null){
                      textUI.displayMsg("Du skal logge ind først");
                      return;
                    }
                    loggedIn = false;
                    textUI.displayMsg("Du er nu logged ud");break;

                default:
                    textUI.displayMsg("Ugyldig valg, prøv igen");
            }
        }
    }

    private void showWatchLater() {textUI.displayMsg("Ingen film i din `Se senere` endnu");
    }
    private void showWatched() {textUI.displayMsg("Ingen sete film endnu");
    }
    private void searchMovie() {
        String title = textUI.promptText("Skriv filmtitel du søger: ");
        boolean found = false;
        for (Movie m: movies){
            if(m.getTitle().toLowerCase().contains(title.toLowerCase())){
                System.out.println(m);
                found = true;
            }
        }
        if (!found) textUI.displayMsg("Ingen film fundet med den titel.");
    }

    private void trendingMovie() {
        textUI.displayMsg("Trending nu: ");
        for(int i = 0; i<Math.min(5, movies.size()); i++){
            System.out.println(movies.get(i));
        }
    }
}