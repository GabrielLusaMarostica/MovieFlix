package br.com.movieflix.mapper;

import br.com.movieflix.DTO.MovieDTO;
import br.com.movieflix.entity.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {

    public Movie map(MovieDTO movieDTO){
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setCategories(movieDTO.getCategories());
        movie.setRating(movieDTO.getRating());
        movie.setTitle(movieDTO.getTitle());
        movie.setDescription(movieDTO.getDescription());
        movie.setStreamings(movieDTO.getStreamings());
        movie.setCreatedAt(movieDTO.getCreatedAt());
        movie.setUpdatedAt(movieDTO.getUpdatedAt());
        movie.setReleaseDate(movieDTO.getReleaseDate());

        return movie;
    }

    public MovieDTO map(Movie movie){
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setCategories(movie.getCategories());
        movieDTO.setRating(movie.getRating());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setStreamings(movie.getStreamings());
        movieDTO.setCreatedAt(movie.getCreatedAt());
        movieDTO.setUpdatedAt(movie.getUpdatedAt());
        movieDTO.setReleaseDate(movie.getReleaseDate());

        return movieDTO;
    }

}
