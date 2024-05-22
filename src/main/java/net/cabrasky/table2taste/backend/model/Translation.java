package net.cabrasky.table2taste.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "translation")
public class Translation implements ModelInterface<Integer> {

    @Id
    @GeneratedValue(generator = "translation_gen")
    @SequenceGenerator(name = "translation_gen", sequenceName = "translation_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "translation_key")
    private String translationKey;

    @Column(name = "value")
    private String value;

    // Getters and setters

    public Integer getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public void setTranslationKey(String translationKey) {
        this.translationKey = translationKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
