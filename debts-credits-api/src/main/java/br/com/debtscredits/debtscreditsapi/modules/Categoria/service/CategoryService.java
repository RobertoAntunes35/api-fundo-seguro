package br.com.debtscredits.debtscreditsapi.modules.Categoria.service;

import br.com.debtscredits.debtscreditsapi.config.exception.ValidationException;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryRequest;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryResponse;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.repository.CategoryRepository;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class CategoryService {
   @Autowired
   private CategoryRepository categoryRepository;

   public List<CategoryResponse> findByDescription(String description) {
      if (isEmpty(description)) {
         throw new ValidationException("The DESCRIPTION must be informed");
      }
      return categoryRepository
              .findByDescriptionIgnoreCaseContaining(description)
              .stream()
              .map(CategoryResponse::of)
              .collect(Collectors.toList());
   }

   public List<CategoryResponse>findAll() {
      return categoryRepository
              .findAll()
              .stream()
              .map(CategoryResponse::of)
              .collect(Collectors.toList());
   }

   public CategoryResponse findByIdResponse(Integer id) {
      return CategoryResponse.of(findById(id));
   }

   public Category findById(Integer id) {
      if (isEmpty(id)) {
         throw new ValidationException("The category ID was not informed");
      }
      return  categoryRepository
              .findById(id)
              .orElseThrow(() -> new ValidationException("There's no supplier fot the given ID"));
   }

   public CategoryResponse save(CategoryRequest request) {
      validateCategoryNameInformed(request);
      var category = categoryRepository.save(Category.of(request));
      return CategoryResponse.of(category);
   }
   private void validateCategoryNameInformed(CategoryRequest request) {
      if (isEmpty(request.getDescription())) {
         throw new ValidationException("The Category Description was not informed");
      }
   }
}
