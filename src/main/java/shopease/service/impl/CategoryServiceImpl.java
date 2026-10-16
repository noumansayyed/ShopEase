package shopease.service.impl;

import org.springframework.stereotype.Service;

import shopease.entity.Category;
import shopease.repository.CategoryRepository;
import shopease.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listAllCategories() {
        return categoryRepository.findAll();
    }
}