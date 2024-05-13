package net.cabrasky.table2taste.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.cabrasky.table2taste.backend.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	List<Category> findByParentCategoryId(Integer parentCategoryId);
}
