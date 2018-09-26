package rentmovie.movieservice.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.validation.BindException;

import rentmovie.movieservice.domain.Movie.Director;

@Slf4j
public class MovieInitializer{

    public MovieInitializer(MovieRepository movieRepository) throws Exception {

        if (movieRepository.count() != 0) {
            return;
        }

        List<Movie> movies = readMovies();

        log.info("Importing {} movies into mongo db", movies.size());
        movieRepository.saveAll(movies);
        log.info("Successfully imported {} movies", movies.size());
    }

    private static List<Movie> readMovies() throws Exception{

        ClassPathResource resource = new ClassPathResource("movies.csv");
        Scanner scanner = new Scanner(resource.getInputStream());
        String line = scanner.nextLine();
        scanner.close();

        FlatFileItemReader<Movie> itemReader = new FlatFileItemReader<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(line.split(","));
        tokenizer.setStrict(false);

        DefaultLineMapper<Movie> lineMapper = new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(MovieFieldSetMapper.INSTANCE);

        itemReader.setResource(resource);
        itemReader.setLineMapper(lineMapper);
        itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
        itemReader.setLinesToSkip(1);
        itemReader.open(new ExecutionContext());

        List<Movie> movies = new ArrayList<>();
        Movie movie = null;

        do {
            movie = itemReader.read();

            if (movie != null){
                movies.add(movie);
            }
        } while (movie != null);

        return movies;
    }

    private enum MovieFieldSetMapper implements FieldSetMapper<Movie>{

        INSTANCE;

        @Override
        public Movie mapFieldSet(FieldSet fields) throws BindException {

            Random random = new Random();

            Director director =
                    new Director(fields.readString("Director Name"), fields.readInt("Director Rate"));

            return Movie.builder()
                    .name(fields.readString("Name"))
                    .genre(MovieGenre.toGenre(fields.readString("Genre")))
                    .leadStudio(fields.readString("Lead Studio"))
                    .audienceScore(fields.readInt("Audience Score"))
                    .price(fields.readBigDecimal("Price"))
                    .year(fields.readString("Year"))
                    .inStockNumber(random.nextInt(6)+10) // gotta add it to the movies.csv
                    .director(director)
                    .build();
        }
    }
}
