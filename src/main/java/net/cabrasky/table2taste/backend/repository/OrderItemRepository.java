package net.cabrasky.table2taste.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.cabrasky.table2taste.backend.model.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
