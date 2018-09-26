package rentmovie.movieservice.provider;

import rentmovie.movieservice.domain.Movie.Director;
import rentmovie.movieservice.domain.Movie;

import rentmovie.movieservice.domain.MovieGenre;
import rentmovie.movieservice.dto.DirectorDto;
import rentmovie.movieservice.dto.MovieDto;
import rentmovie.movieservice.dto.PostMovieDto;

public class MovieConverter implements Converter<PostMovieDto, MovieDto, Movie>{

    @Override
    public Movie update(PostMovieDto updater) {

        Director director = convertToDirector(updater);

        return Movie.builder()
                .id(updater.getId())
                .name(updater.getName())
                .leadStudio(updater.getLeadStudio())
                .genre(MovieGenre.toGenre(updater.getGenre()))
                .audienceScore(updater.getAudienceScore())
                .year(updater.getYear())
                .price(updater.getPrice())
                .inStockNumber(updater.getInStockNumber())
                .director(director)
                .build();
    }

    @Override
    public MovieDto convertToDto(Movie updater) {

        DirectorDto directorDto = convertToDirectorDto(updater);

        return MovieDto.builder()
                .id(updater.getId())
                .name(updater.getName())
                .leadStudio(updater.getLeadStudio())
                .genre(String.valueOf(updater.getGenre()))
                .audienceScore(updater.getAudienceScore())
                .year(updater.getYear())
                .price(updater.getPrice())
                .inStockNumber(updater.getInStockNumber())
                .director(directorDto)
                .build();
    }

    @Override
    public Movie convertToEntity(MovieDto updater) {
        return null;
    }

    private Director convertToDirector(PostMovieDto updater) {
        DirectorDto directorDto = updater.getDirector();

        return new Director(directorDto.getDirectorName(), directorDto.getDirectorRate());
    }

    private DirectorDto convertToDirectorDto(Movie updater) {
        Director director = updater.getDirector();

        return new DirectorDto(director.getDirectorName(), director.getDirectorRate());
    }
}
