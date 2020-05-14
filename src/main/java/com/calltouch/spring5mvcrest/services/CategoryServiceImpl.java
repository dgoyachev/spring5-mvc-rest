package com.calltouch.spring5mvcrest.services;

import com.calltouch.spring5mvcrest.api.v1.mapper.CategoryMapper;
import com.calltouch.spring5mvcrest.api.v1.model.CategoryDTO;
import com.calltouch.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by morgan on 11.05.2020
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        return categoryMapper.categoryToCategoryDTO(categoryRepository.findByName(name));
    }
}
