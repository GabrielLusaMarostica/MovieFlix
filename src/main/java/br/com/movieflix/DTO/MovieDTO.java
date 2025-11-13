package br.com.movieflix.DTO;

import br.com.movieflix.entity.Category;
import br.com.movieflix.entity.Streaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Category> categories;
    private List<Streaming> streamings;
}
