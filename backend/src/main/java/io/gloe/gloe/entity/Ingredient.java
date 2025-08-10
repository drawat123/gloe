package io.gloe.gloe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ingredients")
@Getter
@Setter
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "canonical_name")
    private String canonical_name;

    /**
     * A list of alternative names (synonyms) for this ingredient.
     * <p>
     * Stored in a separate table "ingredient_synonyms" linked by ingredient_id.
     *
     * @ElementCollection is the mandatory annotation to indicate that this field is a collection of basic
     * or embeddable types that should be stored in a separate table.
     */
    @ElementCollection
    @CollectionTable(name = "ingredient_synonyms", joinColumns = @JoinColumn(name = "ingredient_id"))
    @Column(name = "synonym")
    private List<String> synonyms = new ArrayList<>();

    @Column(name = "ewg_score")
    private Integer ewgScore; // Environmental Working Group score

    @Column(name = "pregnancy_risk")
    @Enumerated(EnumType.STRING)
    private PregnancyRisk pregnancyRisk; // SAFE, CAUTION, AVOID

    @Column(name = "hormonal_disruptor")
    private boolean hormonalDisruptor;

    @Column(length = 2000, name = "notes")
    private String notes;

    public enum PregnancyRisk {
        SAFE, CAUTION, AVOID
    }
}
