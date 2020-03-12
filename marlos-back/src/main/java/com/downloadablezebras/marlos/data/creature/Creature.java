package com.downloadablezebras.marlos.data.creature;

import com.downloadablezebras.marlos.data.damagemodifier.DamageModifier;
import com.downloadablezebras.marlos.data.statuscondition.StatusCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode
public class Creature {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = true)
    private int level;

    private String size;

    private String type;

    private String alignment;

    @Column(nullable = true)
    private int ac;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "average", column = @Column(name = "average_hp")),
            @AttributeOverride(name = "hit_die", column = @Column(name = "hit_die"))
    })
    private HP hp;

    private String speed;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "strength", column = @Column(name = "strength")),
            @AttributeOverride(name = "dexterity", column = @Column(name = "dexterity")),
            @AttributeOverride(name = "constitution", column = @Column(name = "constitution")),
            @AttributeOverride(name = "intelligence", column = @Column(name = "intelligence")),
            @AttributeOverride(name = "wisdom", column = @Column(name = "wisdom")),
            @AttributeOverride(name = "charisma", column = @Column(name = "charisma"))
    })
    private Abilities abilities;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "strength", column = @Column(name = "saving_strength")),
            @AttributeOverride(name = "dexterity", column = @Column(name = "saving_dexterity")),
            @AttributeOverride(name = "constitution", column = @Column(name = "saving_constitution")),
            @AttributeOverride(name = "intelligence", column = @Column(name = "saving_intelligence")),
            @AttributeOverride(name = "wisdom", column = @Column(name = "saving_wisdom")),
            @AttributeOverride(name = "charisma", column = @Column(name = "saving_charisma"))
    })
    private Abilities savingThrows;

    private String skills;

    @ManyToMany
    @JoinTable(
            name="creature_damageModifier",
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name= "damage_modifier_id"))
    private List<DamageModifier> damageModifiers = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name="creature_conditionImmunity",
            joinColumns = @JoinColumn(name = "creature_id"),
            inverseJoinColumns = @JoinColumn(name = "condition_immunity_id"))
    private List<StatusCondition> conditionImmunities = new ArrayList<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "blindsight", column = @Column(name = "blindsight")),
            @AttributeOverride(name = "darkvision", column = @Column(name = "darkvision")),
            @AttributeOverride(name = "truesight", column = @Column(name = "truesight"))
    })
    private Senses senses;

    private String languages;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rating", column = @Column(name = "challenge_rating")),
            @AttributeOverride(name = "experience", column = @Column(name = "experience"))
    })
    private ChallengeRating cr;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "book", column = @Column(name = "text_reference_book")),
            @AttributeOverride( name = "pageNumber", column = @Column(name = "text_reference_page_number"))
    })
    private TextReference textReference;

    public Creature() {

    }

    public Creature(String name, int level, String size, String type, String alignment, int ac, HP hp, String speed, Abilities abilities,
                    Abilities savingThrows, String skills, List<DamageModifier> damageModifiers,
                    List<StatusCondition> conditionImmunities, Senses senses, String languages, ChallengeRating cr,
                    TextReference textReference) {
        this.name = name;
        this.level = level;
        this.size = size;
        this.type = type;
        this.alignment = alignment;
        this.ac = ac;
        this.hp = hp;
        this.speed = speed;
        this.abilities = abilities;
        this.savingThrows = savingThrows;
        this.skills = skills;
        this.damageModifiers = damageModifiers;
        this.conditionImmunities = conditionImmunities;
        this.senses = senses;
        this.languages = languages;
        this.cr = cr;
        this.textReference = textReference;
    }
}
