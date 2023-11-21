package br.com.debtscredits.debtscreditsapi.modules.Categoria.controller;

import br.com.debtscredits.debtscreditsapi.config.SucessResponse;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryRequest;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.dto.CategoryResponse;
import br.com.debtscredits.debtscreditsapi.modules.Categoria.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return categoryService.save(request);
    }

    @GetMapping
    public List<CategoryResponse>findAll() {
        return categoryService.findAll();
    }

    @GetMapping("{id}")
    public CategoryResponse findById(@PathVariable Integer id) {
        return categoryService.findByIdResponse(id);
    }

    @GetMapping("description/{description}")
    public List<CategoryResponse>findByDescription(@PathVariable String description) {
        return categoryService.findByDescription(description);
    }
    
    @PutMapping("{id}")
    public CategoryResponse update(@RequestBody CategoryRequest request, @PathVariable Integer id) {
        return categoryService.update(request, id);
    }
    @DeleteMapping("{id}")
    public SucessResponse delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return SucessResponse.create("The category was deleted");
    }
}
