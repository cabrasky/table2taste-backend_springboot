package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.model.Category;
import net.cabrasky.table2taste.backend.modelDto.CategoryDTO;
import net.cabrasky.table2taste.backend.service.CategoryService;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractModificableController<Category, CategoryDTO, Integer, CategoryService>{

	@GetMapping("/filters/")
	public List<Category> getAllFiltered(@RequestParam(required = false) Integer parentCategoryId) {
		return service.getAllByParentCategoryId(parentCategoryId);
	}
}
