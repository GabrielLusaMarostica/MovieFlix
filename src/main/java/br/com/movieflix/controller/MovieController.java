package br.com.movieflix.controller;

import br.com.movieflix.DTO.MovieDTO;
import br.com.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO){
        MovieDTO movie = movieService.save(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(movie);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies(){
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<MovieDTO>> getMovieById(@PathVariable Long id){
        Optional<MovieDTO> movieDTO = movieService.findById(id);
        if(movieDTO.isPresent()) {
            return ResponseEntity.ok(movieDTO);
        } else{
            return null;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id){
        if(movieService.findById(id).isPresent()){
            movieService.deleteMovie(id);
            return ResponseEntity.ok("O movie de id " + id + " foi deletado com sucesso");
        } else{
            return ResponseEntity.ok("O movie de id " + id + " não foi encontrado");
        }
    }
}
