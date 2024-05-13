package net.cabrasky.table2taste.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.cabrasky.table2taste.backend.model.Language;

public interface LanguageRepository extends JpaRepository<Language, String>{

}