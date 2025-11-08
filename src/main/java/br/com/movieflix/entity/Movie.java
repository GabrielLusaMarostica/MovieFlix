package br.com.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    private double rating;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Many-to-Many SEMPRE precisa de uma tabela no meio
    // Movie <--> movie_category <--> Category
    //A tabela do meio guarda os IDs das duas tabelas.
    @ManyToMany
    @JoinTable(name = "movie_category", //Sempre que salvar um Movie, o JPA vai gravar os IDs na tabela movie_category.
            joinColumns = @JoinColumn(name = "movie_id"), //quer dizer “este lado é o Movie”
            inverseJoinColumns = @JoinColumn(name = "category_id"))  //quer dizer “este é o lado Category”
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "movie_streaming", // tabela intermediaria que vai salvar o id dos movies e dos streamings
            joinColumns = @JoinColumn(name = "movie_id"), // quer dizer que deste lado vai salvar o id dos movies
            inverseJoinColumns = @JoinColumn(name = "streaming_id"))  // e deste lado vai salvar o id dos streamings
    private List<Streaming> streamings;

}
