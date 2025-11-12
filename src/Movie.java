public class Movie {

    private String title;
    private int yearOfRealise;
    String[] categories;
    private double rating;

    public Movie(String title, int yearOfRealise, String[] categories, double rating) {
        this.title = title;
        this.yearOfRealise = yearOfRealise;
        this.categories = categories;
        this.rating = rating;

    }
    public String getTitle() {
        return title;
    }
    public int getYearOfRealise() {
        return yearOfRealise;
    }

    public double getRating() {
        return rating;

    }
    public String[] getCategories(){
        return categories;
    }
    @Override
    public String toString() {
        String s = title + " " + yearOfRealise + " ";
        for(String c: categories){
            s += c + " ";
        }
        return s + rating;
    }

    public void playMovie(){
        System.out.println( this.title + " spiller nu");

    }
}

