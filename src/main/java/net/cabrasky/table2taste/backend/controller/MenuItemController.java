package net.cabrasky.table2taste.backend.controller;

import net.cabrasky.table2taste.backend.model.MenuItem;
import net.cabrasky.table2taste.backend.modelDto.MenuItemDTO;
import net.cabrasky.table2taste.backend.service.MenuItemService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/menuItems")
public class MenuItemController extends AbstractModificableController<MenuItem, MenuItemDTO, Integer, MenuItemService> {
	
	@GetMapping("/filters/")
	public List<MenuItem> getAllFiltered(@RequestParam(required = false) List<String> allergenIds,
			@RequestParam(required = false) String categoryId) {
		return service.getMenuItemsByAllergensAndCategory(allergenIds, categoryId);
	}
}
