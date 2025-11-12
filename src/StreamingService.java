import utils.FileIO;
import utils.TextUI;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class StreamingService {
    FileIO fileIO = new FileIO();
    TextUI textUI = new TextUI();
    ArrayList<User> users = new ArrayList<>();
    boolean running = true;
    User loggedInUser;

    ArrayList<Movie> movies = new ArrayList<>();

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
        textUI.displayMsg("Type the number of the movie");
        int counter = 0;
        for (Movie m : movies) {
            counter++;
            System.out.println(counter + ". " + m.toString());
        }
        int userInput = textUI.promptNumeric("");
        Movie m = movies.get(userInput - 1);
        m.playMovie();
    }

    public void showMovieByCategory(String category) {
        textUI.displayMsg("Showing all " + category + " movies");
        int counter = 0;
        for (Movie m : movies) {
            if (Arrays.asList(m.categories).contains(category)) {
                counter++;
                System.out.println(counter + ". " + m.toString());
            }
        }
        if( counter > 0){
        int userInput = textUI.promptNumeric("choose a movie");
        Movie m = movies.get(userInput - 1);
        m.playMovie();
    }else{
            textUI.displayMsg("Vi fandt ingen film");
            searchByCategory();
        }

    }
    public void start() {
        textUI.displayMsg("***********Velkomme to CHILL***************");
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
                    textUI.displayMsg("Tak for besøge!");break;
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
            if (u.getUsername().equals(userName) && u.getPassword().equals(password)) ;
            loggedInUser = u;
            textUI.displayMsg("Du er log in som " + userName);
            mainMenu();
            return;
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
                    searchByCategory();break;
                case 4:
                    showWatched();break;
                case 5:
                    showWatchLater();break;
                case 6:
                    loggedInUser = null;
                    loggedIn = false;
                    textUI.displayMsg("Du er nu logged ud");break;

                default:
                    textUI.displayMsg("Ugyldig valg, prøv igen");
            }
        }
    }

    private void showWatchLater() {
    }

    private void showWatched() {
    }

    private void searchByCategory() {
        String input = textUI.promptText("Tæst Kategori");
        showMovieByCategory(input);
    }

    private void searchMovie() {
    }

    private void trendingMovie() {
    }
}