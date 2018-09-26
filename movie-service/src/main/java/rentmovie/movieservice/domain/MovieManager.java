package rentmovie.movieservice.domain;

public class MovieManager {

    public Movie actualizeStock(Movie movie, String action) {

        Integer inStock = movie.getInStockNumber();

        if (action.equalsIgnoreCase("subtract")){
            return movie.withInStockNumber(inStock - 1);
        } else {
            return movie.withInStockNumber(inStock + 1);
        }
    }

}
