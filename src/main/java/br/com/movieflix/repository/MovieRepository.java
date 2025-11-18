package br.com.movieflix.repository;

import br.com.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    //é preciso implementar a query direto no repository para buscar um movie pela category
    @Query("SELECT m FROM Movie m JOIN m.categories c WHERE c.id = :categoryId")
    List<Movie> findByCategoryId(Long categoryId);


}
