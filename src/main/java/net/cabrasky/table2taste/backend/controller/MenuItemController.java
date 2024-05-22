package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.model.Category;
import net.cabrasky.table2taste.backend.model.MenuItem;
import net.cabrasky.table2taste.backend.modelDto.MenuItemDTO;
import net.cabrasky.table2taste.backend.service.MenuItemService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/menuItems")
public class MenuItemController extends AbstractModificableController<MenuItem, MenuItemDTO, String, MenuItemService> {	
	
	@GetMapping(path = "/ancestors", params = { "id" })
	public List<Category> getAllParents(@RequestParam(required = true) String id) {
		return service.getCategoryAncestors(id);
	}

	@GetMapping(params = { "categoryId", "allergenIds" })
	public List<MenuItem> getAllFiltered(@RequestParam List<String> allergenIds, String categoryId) {
		return service.getMenuItemsByAllergensAndCategory(allergenIds, categoryId.isEmpty() ? null : categoryId);
	}

	@GetMapping(params = { "categoryId" })
	public List<MenuItem> getAllFilteredByCategory(@RequestParam String categoryId) {
		return service.getMenuItemsByAllergensAndCategory(null, categoryId.isEmpty() ? null : categoryId);
	}
}
