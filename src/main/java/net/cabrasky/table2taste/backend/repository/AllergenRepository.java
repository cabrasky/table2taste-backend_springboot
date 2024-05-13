package net.cabrasky.table2taste.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import net.cabrasky.table2taste.backend.model.Allergen;

import java.util.List;

public interface AllergenRepository extends JpaRepository<Allergen, String> {
    List<Allergen> findByIdInAndInclusive(List<String> ids, boolean inclusive);
}
