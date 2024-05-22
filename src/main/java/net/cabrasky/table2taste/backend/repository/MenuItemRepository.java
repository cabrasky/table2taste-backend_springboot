package net.cabrasky.table2taste.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.cabrasky.table2taste.backend.model.MenuItem;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, String> {
	@Query("SELECT m FROM MenuItem m WHERE m.category.id = :categoryId OR (:categoryId IS NULL AND m.category.id IS NULL)")
    List<MenuItem> findByCategoryId(String categoryId);


}
