package rentmovie.movieservice.domain;

import java.util.Arrays;

public enum MovieGenre {

    COMEDY, HORROR, THRILLER,
    ROMANCE, FANTASY, CRIME,
    DRAMA, ANIMATION,ACTION;

    public static MovieGenre toGenre(String givenGenre) {
        return Arrays.stream(MovieGenre.values())
                .filter(genre -> isCorrect(genre, givenGenre))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static boolean isCorrect(MovieGenre genre, String givenGenre) {
        return genre.name().equalsIgnoreCase(givenGenre);
    }
}