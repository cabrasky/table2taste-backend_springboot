package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.model.Category;
import net.cabrasky.table2taste.backend.modelDto.CategoryDTO;
import net.cabrasky.table2taste.backend.service.CategoryService;

import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/categories")
public class CategoryController extends AbstractModificableController<Category, CategoryDTO, String, CategoryService>{

	@GetMapping(params = {"parentCategoryId"})
	public List<Category> getAllFiltered(@RequestParam(required = true) String parentCategoryId) {
		return service.getAllByParentCategoryId(parentCategoryId.isEmpty() ? null : parentCategoryId);
	}
	
	@GetMapping(path = "/ancestors", params = {"id"})
	public List<Category> getAllParents(@RequestParam(required = true) String id) {
		return service.getCategoryAncestors(id);
	}
}
