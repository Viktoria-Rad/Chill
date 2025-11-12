public class Movie {

    private String title;
    private int yearOfRealise;
    //Category Category;
    String[] categories;
    private double rating;

    public Movie(String title, int yearOfRealise, String[] categories, double rating) {
        this.title = title;
        this.yearOfRealise = yearOfRealise;
        this.categories = categories;
        //Category = Category;
        this.rating = rating;
    }
    public String getTitle() {
        return title;
    }
    public int getYearOfRealise() {
        return yearOfRealise;
    }
    //public Category getCategory() {
       // return Category;
   // }
    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        String s = title + " " + yearOfRealise + " ";
        for(String c: categories){
            s += c + " ";
        }
        return s + rating;
    }
   /* public double playMovie(Category Category;){
        switch (Category) {
            case crime:
                return title;
            case war:
                return title;
            case drama:
                return title;
            case family:
                return title;
            case romance:
                return title;
            case scifi:
                return title;
            default:
                return title;
        }

    }*/
    public void playMovie(){
        System.out.println( this.title + " is x playing");

    }
}

