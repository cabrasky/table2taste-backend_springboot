package net.cabrasky.table2taste.backend.model;

import java.sql.Timestamp;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
@jakarta.persistence.Table(name = "service")
public class Service implements ModelInterface<Long> {
	@Id
	@GeneratedValue(generator = "service_gen")
	@SequenceGenerator(name = "service_gen", sequenceName = "service_id_seq", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "table_id")
	private Table apptable;

	@CreationTimestamp
	@Column(name = "open_timestamp")
	private Timestamp openTimestamp;

	@Column(name = "close_timestamp")
	@Nullable
	private Timestamp closeTimestamp;

	@Column(name = "is_open")
	private Boolean isOpen;

	@OneToMany(mappedBy = "service")
	private Set<Order> orders;

	// Getters and setters

	public Long getId() {
		return id;
	}

	public Table getApptable() {
		return apptable;
	}

	public void setApptable(Table apptable) {
		this.apptable = apptable;
	}

	public Timestamp getOpenTimestamp() {
		return openTimestamp;
	}
	
	public Timestamp getCloseTimestamp() {
		return closeTimestamp;
	}

	public void setCloseTimestamp(Timestamp closeTimestamp) {
		this.closeTimestamp = closeTimestamp;
	}

	public Boolean getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

}
