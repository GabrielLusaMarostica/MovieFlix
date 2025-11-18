package br.com.movieflix.service;

import br.com.movieflix.DTO.CategoryDTO;
import br.com.movieflix.DTO.MovieDTO;
import br.com.movieflix.DTO.StreamingDTO;
import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Movie;
import br.com.movieflix.entity.Streaming;
import br.com.movieflix.mapper.MovieMapper;
import br.com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    public MovieDTO save(MovieDTO movieDTO){
        Movie movie = movieMapper.map(movieDTO);
        movieRepository.save(movie);
        return movieMapper.map(movie);
    }

    public List<MovieDTO> findAll(){
        List<Movie> movies = movieRepository.findAll();
        List<MovieDTO> resultado = new ArrayList<>();
        //para cada movie na lista, transforma em dto e coloca na lista do resultado para retornar ao usuario
        for(Movie movie : movies){
            MovieDTO dto = movieMapper.map(movie);
            resultado.add(dto);
        }
        return resultado;
    }

    public Optional<MovieDTO> findById(Long id){
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(movieMapper::map);
    }

    public List<MovieDTO> findMovieByCategory(Long categoryId) {
        List<Movie> movies = movieRepository.findByCategoryId(categoryId);
        List<MovieDTO> resultado = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDTO dto = movieMapper.map(movie);
            resultado.add(dto);
        }

        return resultado;
    }


    public MovieDTO updateMovie(Long id, MovieDTO movieDTO){
        Optional<Movie> movie = movieRepository.findById(id);
        if(movie.isPresent()){
            Movie movieAtualizado = movieMapper.map(movieDTO);
            movieAtualizado.setId(id);
            Movie movieSalvo = movieRepository.save(movieAtualizado);
            return movieMapper.map(movieSalvo);
        }
        return null;
    }

    public void deleteMovie(Long id){
        movieRepository.deleteById(id);
    }

}
