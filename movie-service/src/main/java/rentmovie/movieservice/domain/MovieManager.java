package rentmovie.movieservice.domain;

public class MovieManager {


    public Movie actualizeStock(Movie movie, String action) {

        Integer inStock = movie.getInStockNumber();

        if (action.equalsIgnoreCase("subtract")){
            movie.withInStockNumber(inStock - 1);
        } else {
            movie.withInStockNumber(inStock + 1);
        }

        return movie;
    }
}
