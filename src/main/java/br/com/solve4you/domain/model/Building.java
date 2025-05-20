package br.com.solve4you.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Building {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Positive
    private int stage;

    @Getter
    @NotNull
    @Size(min=2,max = 60)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Column(name = "qtdFood")
    @PositiveOrZero
    private long qtdFood;

    @Column(name = "qtdOil")
    @PositiveOrZero
    private long qtdOil;

    @Column(name = "qtdIron")
    @PositiveOrZero
    private long qtdIron;

    @Column(name = "qtdMinerals")
    @PositiveOrZero
    private long qtdMinerals;

    @Column(name = "qtdUranium")
    @PositiveOrZero
    private int qtdUranium;

    @PositiveOrZero
    private String troopType;

    @PositiveOrZero
    private long recruitingSpeed;

    @PositiveOrZero
    private int woundedCapacity;

    @PositiveOrZero
    private int healingSpeed;

    @PositiveOrZero
    private int fleetLimitBonus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "building_requirements",
            joinColumns = @JoinColumn(name = "building_id"),
            inverseJoinColumns = @JoinColumn(name = "requirements_id"))
    @JsonIgnore
    private List<Building> buildingRequirements;

}
