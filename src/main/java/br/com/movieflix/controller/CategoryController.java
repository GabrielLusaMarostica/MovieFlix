package br.com.movieflix.controller;

import br.com.movieflix.DTO.CategoryDTO;
import br.com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movieflix/category") //define a url do site
@RequiredArgsConstructor // cria os construtores com lombok
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDTO category = categoryService.saveCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryId(@PathVariable Long id) {
        Optional<CategoryDTO> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get());
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByCategoryId(@PathVariable Long id) {
        if (categoryService.findById(id).isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Categoria de id " + id + " deletada com sucesso");
        } else {
            return ResponseEntity.ok("A categoria de id " + id + " não foi encontrada");
        }

    }

}
