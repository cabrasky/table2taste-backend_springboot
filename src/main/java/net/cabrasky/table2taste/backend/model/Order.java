package net.cabrasky.table2taste.backend.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_order")
public class Order implements ModelInterface<Long> {

	@Id
	@GeneratedValue(generator = "order_gen")
	@SequenceGenerator(name = "order_gen", sequenceName = "order_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

	@CreationTimestamp
	@Column(name = "created_on")
	private Timestamp createdOn;

	// Getters and setters

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

}
