package net.cabrasky.table2taste.backend.service;

import net.cabrasky.table2taste.backend.model.Category;
import net.cabrasky.table2taste.backend.modelDto.CategoryDTO;
import net.cabrasky.table2taste.backend.repository.CategoryRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoryService extends AbstractModificableService<Category, CategoryDTO, Integer, CategoryRepository> {

	public List<Category> getAllByParentCategoryId(Integer parentCategoryId) {

		return repository.findByParentCategoryId(parentCategoryId);
	}

	@Override
	protected Class<Category> getModelClass() {
		return Category.class;
	}
}