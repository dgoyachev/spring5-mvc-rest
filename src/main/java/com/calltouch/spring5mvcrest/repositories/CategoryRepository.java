package com.calltouch.spring5mvcrest.repositories;

import com.calltouch.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by morgan on 11.05.2020
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
