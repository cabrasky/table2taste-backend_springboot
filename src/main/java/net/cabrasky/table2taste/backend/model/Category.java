package net.cabrasky.table2taste.backend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements ModificableModelInterface<Integer> {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "media_url")
	private String mediaUrl;

	@ManyToOne
	@JoinColumn(name = "parent_category_id")
	private Category parentCategory;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "category_translation", joinColumns = @JoinColumn(name = "category_id"), inverseJoinColumns = @JoinColumn(name = "translation_id"))
	private Set<Translation> translations;

	// Getters and setters

	public Integer getId() {
		return id;
	}
	
	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Translation> getTranslations() {
		return this.translations;
	}

	public void setTranslations(Set<Translation> translations) {
		this.translations = translations;
    }
}
