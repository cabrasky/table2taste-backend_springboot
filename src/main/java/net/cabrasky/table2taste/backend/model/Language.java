package net.cabrasky.table2taste.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "language")
public class Language implements ModelInterface<String> {
	@Id
	@Column(name = "id")
	private String id;

	public String getId() {
		return id;
	}
}
