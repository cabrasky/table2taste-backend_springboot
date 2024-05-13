package net.cabrasky.table2taste.backend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_item")
public class MenuItem implements ModificableModelInterface<Integer> {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "price")
	private double price;

	@Column(name = "media_url")
	private String mediaUrl;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "menu_item_translation", joinColumns = @JoinColumn(name = "menu_item_id"), inverseJoinColumns = @JoinColumn(name = "translation_id"))
	private Set<Translation> translations;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "menu_item_allergen", joinColumns = @JoinColumn(name = "menu_item_id"), inverseJoinColumns = @JoinColumn(name = "allergen_id"))
	private Set<Allergen> allergens;

	// Getters and setters

	public Integer getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(Set<Translation> translations) {
		this.translations = translations;
    }

	public Set<Allergen> getAllergens() {
		return allergens;
	}

	public void setAllergens(Set<Allergen> allergens) {
		this.allergens = allergens;
    }
}