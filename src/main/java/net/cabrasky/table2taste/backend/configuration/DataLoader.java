package net.cabrasky.table2taste.backend.configuration;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.cabrasky.table2taste.backend.model.Allergen;
import net.cabrasky.table2taste.backend.model.Category;
import net.cabrasky.table2taste.backend.model.Group;
import net.cabrasky.table2taste.backend.model.Language;
import net.cabrasky.table2taste.backend.model.MenuItem;
import net.cabrasky.table2taste.backend.model.Privilage;
import net.cabrasky.table2taste.backend.model.Translation;
import net.cabrasky.table2taste.backend.model.User;
import net.cabrasky.table2taste.backend.repository.AllergenRepository;
import net.cabrasky.table2taste.backend.repository.CategoryRepository;
import net.cabrasky.table2taste.backend.repository.GroupRepository;
import net.cabrasky.table2taste.backend.repository.LanguageRepository;
import net.cabrasky.table2taste.backend.repository.MenuItemRepository;
import net.cabrasky.table2taste.backend.repository.PrivilageRepository;
import net.cabrasky.table2taste.backend.repository.UserRepository;

@Configuration
public class DataLoader {

	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private AllergenRepository allergenRepository;

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Autowired
	private PrivilageRepository privilageRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public ApplicationRunner initData() {
		return args -> {

			try (FileReader fileReader = new FileReader("defaultData.json")) {
				JsonObject defaultData = JsonParser.parseReader(fileReader).getAsJsonObject();

				if (languageRepository.count() == 0) {
					loadDefaultLanguages(defaultData);
				} else {
					logger.info("Languages already exist. Skipping data loading.");
				}

				if (allergenRepository.count() == 0) {
					loadDefaultAllergens(defaultData);
				} else {
					logger.info("Allergens already exist. Skipping data loading.");
				}

				if (privilageRepository.count() == 0) {
					loadDefaultPrivilages(defaultData);
				} else {
					logger.info("Privilages already exist. Skipping data loading.");
				}

				if (groupRepository.count() == 0) {
					loadDefaultGroups(defaultData);
				} else {
					logger.info("Groups already exist. Skipping data loading.");
				}

				if (userRepository.count() == 0) {
					loadDefaultUsers(defaultData);
				} else {
					logger.info("Users already exist. Skipping data loading.");
				}

				if (categoryRepository.count() == 0) {
					loadDefaultCategories(defaultData);
				} else {
					logger.info("Categories already exist. Skipping data loading.");
				}

			} catch (IOException e) {
				System.err.println("Error reading defaulData.json: " + e.getMessage());
				e.printStackTrace();
			}

		};
	}

	@Transactional
	public void loadDefaultLanguages(JsonObject data) {
		for (JsonElement languageElement : data.get("languages").getAsJsonArray()) {
			Language language = new Language();
			language.setId(languageElement.getAsString());
			languageRepository.save(language);
		}
	}

	@Transactional
	public void loadDefaultAllergens(JsonObject data) {
		for (JsonElement allergenElement : data.get("allergens").getAsJsonArray()) {
			JsonObject allergenObject = allergenElement.getAsJsonObject();
			allergenRepository.save(getAllergen(allergenObject));
		}
	}

	@Transactional
	public void loadDefaultPrivilages(JsonObject data) {
		for (JsonElement privilageElement : data.get("privilages").getAsJsonArray()) {
			Privilage privilage = new Privilage();
			privilage.setId(privilageElement.getAsString());
			privilageRepository.save(privilage);
		}
	}

	@Transactional
	public void loadDefaultGroups(JsonObject data) {
		for (JsonElement groupElement : data.get("groups").getAsJsonArray()) {
			JsonObject groupObject = groupElement.getAsJsonObject();
			saveGroup(groupObject);
		}
	}

	@Transactional
	public void loadDefaultUsers(JsonObject data) {
		for (JsonElement userElement : data.get("users").getAsJsonArray()) {
			JsonObject userObject = userElement.getAsJsonObject();
			saveUser(userObject);
		}
	}

	private Allergen getAllergen(JsonObject allergenObject) {
		Allergen allergen = new Allergen();
		allergen.setId(allergenObject.get("id").getAsString());
		allergen.setMediaUrl(allergenObject.get("media_url").getAsString());
		allergen.setInclusive(allergenObject.get("inclusive").getAsBoolean());
		allergen.setTranslations(getTranslations(allergenObject));
		return allergen;
	}

	private void saveGroup(JsonObject groupObject) {
		Group group = new Group();
		group.setId(groupObject.get("id").getAsString());
		group.setColor(groupObject.get("color").getAsString());
		group.setTranslations(getTranslations(groupObject));
		group.setPrivilages(getPrivilages(groupObject));
		groupRepository.save(group);
	}

	private void saveUser(JsonObject userObject) {
		User user = new User();
		user.setUsername(userObject.get("username").getAsString());
		user.setName(userObject.get("photo_url").getAsString());
		user.setPassword(userObject.get("password").getAsString());
		user.setGroups(getGroups(userObject));
		userRepository.save(user);
	}

	@Transactional
	public void loadDefaultCategories(JsonObject data) {
		for (JsonElement categoryElement : data.get("categories").getAsJsonArray()) {
			JsonObject categoryObject = categoryElement.getAsJsonObject();
			saveCategory(categoryObject);
		}
	}

	private void saveCategory(JsonObject categoryObject) {
		Set<Translation> categoryTranslations = getTranslations(categoryObject);
		String categoryId = categoryObject.get("id").getAsString();
		Category category = new Category();
		category.setId(categoryId);
		category.setMediaUrl("");
		category.setTranslations(categoryTranslations);
		categoryRepository.save(category);
		saveMenuItems(categoryObject, categoryId);
	}

	private void saveMenuItems(JsonObject categoryObject, String categoryId) {
		JsonArray menuItemArray = categoryObject.getAsJsonArray("menuItems");

		for (var menuItemElement : menuItemArray) {
			JsonObject menuItemObject = menuItemElement.getAsJsonObject();
			MenuItem menuItem = getMenuItem(menuItemObject);
			menuItem.setCategoryId(categoryId);
			menuItemRepository.save(menuItem);
		}
	}

	private MenuItem getMenuItem(JsonObject menuItemObject) {
		Set<Translation> menuItemTranslations = getTranslations(menuItemObject);
		Set<Allergen> menuItemAllergens = getAllergens(menuItemObject);
		String menuItemId = menuItemObject.get("id").getAsString();
		double price = menuItemObject.get("price").getAsDouble();
		MenuItem menuItem = new MenuItem();
		menuItem.setId(menuItemId);
		menuItem.setPrice(price);
		menuItem.setMediaUrl("");
		menuItem.setTranslations(menuItemTranslations);
		menuItem.setAllergens(menuItemAllergens);
		return menuItem;
	}

	private Set<Allergen> getAllergens(JsonObject object) {
		Set<Allergen> allergens = new HashSet<>();
		JsonArray allergensArray = object.getAsJsonArray("allergens");

		allergensArray.forEach(allergenElement -> {
			String allergenId = allergenElement.getAsString();
			Allergen allergen = allergenRepository.findById(allergenId).orElse(null);
			if (allergen != null) {
				allergens.add(allergen);
			}
		});
		return allergens;
	}

	private Set<Privilage> getPrivilages(JsonObject object) {
		Set<Privilage> privilages = new HashSet<>();
		JsonArray privilagesArray = object.getAsJsonArray("privilages");

		privilagesArray.forEach(privilageElement -> {
			String privilageId = privilageElement.getAsString();
			Privilage privilage = privilageRepository.findById(privilageId).orElse(null);
			if (privilage != null) {
				privilages.add(privilage);
			}

		});
		return privilages;
	}

	private Set<Group> getGroups(JsonObject object) {
		Set<Group> groups = new HashSet<>();
		JsonArray groupsArray = object.getAsJsonArray("groups");

		groupsArray.forEach(groupElement -> {
			String groupId = groupElement.getAsString();
			Group group = groupRepository.findById(groupId).orElse(null);
			if (group != null) {
				groups.add(group);
			}

		});
		return groups;
	}

	private Set<Translation> getTranslations(JsonObject object) {
		Set<Translation> translations = new HashSet<>();
		JsonObject translationsObject = object.getAsJsonObject("translations");

		for (var entry : translationsObject.entrySet()) {
			String languageId = entry.getKey();
			JsonObject languageTranslations = entry.getValue().getAsJsonObject();
			Language language = languageRepository.findById(languageId)
					.orElseThrow(() -> new IllegalArgumentException("Language not found: " + languageId));
			for (var translationEntry : languageTranslations.entrySet()) {
				String key = translationEntry.getKey();
				String value = translationEntry.getValue().getAsString();
				Translation translation = new Translation();
				translation.setTranslationKey(key);
				translation.setValue(value);
				translation.setLanguage(language);
				translations.add(translation);
			}
		}
		return translations;
	}
}
