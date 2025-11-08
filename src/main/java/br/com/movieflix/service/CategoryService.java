package br.com.movieflix.service;

import br.com.movieflix.DTO.CategoryDTO;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // cria os construtores com lombok
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> findAll(){
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> resultado = new ArrayList<>();
        for(Category category : categories){
            CategoryDTO dto = categoryMapper.map(category);
            resultado.add(dto);
        }
        return resultado;
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDTO){
        Category category = categoryMapper.map(categoryDTO);
        categoryRepository.save(category);
        return categoryMapper.map(category);
    }

    public Optional<CategoryDTO> findById(Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(categoryMapper::map); //usa o metodo map no category, que é um metodo dos optional que
        // transforma o valor dentro dele caso esse valor exista. O ::map é o mesmo que categoryMapper.map(category). Ou seja ele vai
        // transformar o category em um dto caso ele seja valido
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
