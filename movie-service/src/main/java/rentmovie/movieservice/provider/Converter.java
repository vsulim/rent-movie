package rentmovie.movieservice.provider;

public interface Converter<T, U, R> {

    R update(T updater);

    U convertToDto(R updater);

    R convertToEntity(U updater);
}
