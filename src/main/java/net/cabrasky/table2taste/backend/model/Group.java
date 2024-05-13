package net.cabrasky.table2taste.backend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "group")
public class Group implements ModificableModelInterface<String> {
	@Id
	@Column(name = "id")
	private String id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "group_privilage", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "privilage_id"))
	private Set<Privilage> privilages;

	@Column(name = "color")
	private String color;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "group_translation", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "translation_id"))
	private Set<Translation> translations;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "group_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	public String getId() {
		return id;
	}

	public Set<Privilage> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(Set<Privilage> newPrivilages) {
		this.privilages.clear();

		if (newPrivilages != null) {
			this.privilages.addAll(newPrivilages);
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Translation> getTranslations() {
		return this.translations;
	}

	public void setTranslations(Set<Translation> translations) {
		this.translations = translations;
    }
	
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
    }

}
