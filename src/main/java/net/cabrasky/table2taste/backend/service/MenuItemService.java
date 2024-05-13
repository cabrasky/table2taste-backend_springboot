package net.cabrasky.table2taste.backend.service;

import net.cabrasky.table2taste.backend.model.Allergen;
import net.cabrasky.table2taste.backend.model.MenuItem;
import net.cabrasky.table2taste.backend.modelDto.MenuItemDTO;
import net.cabrasky.table2taste.backend.repository.AllergenRepository;
import net.cabrasky.table2taste.backend.repository.MenuItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class MenuItemService extends AbstractModificableService<MenuItem, MenuItemDTO, Integer, MenuItemRepository> {
	@Autowired
	private AllergenRepository allergenRepository;

	public List<MenuItem> getMenuItemsByAllergensAndCategory(List<String> allergenIds, String categoryId) {

		List<Allergen> inclusiveAllergens = allergenRepository.findByIdInAndInclusive(allergenIds, true);
		List<Allergen> exclusiveAllergens = allergenRepository.findByIdInAndInclusive(allergenIds, false);
		
		return repository.findByCategoryId(categoryId).stream()
				.filter(menuItem -> menuItem.getAllergens().containsAll(inclusiveAllergens))
				.filter(menuItem -> menuItem.getAllergens().stream().noneMatch(exclusiveAllergens::contains))
				.toList();
	}

	@Override
	protected Class<MenuItem> getModelClass() {
		return MenuItem.class;
	}

}