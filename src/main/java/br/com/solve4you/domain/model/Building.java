package br.com.solve4you.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    @Size(max = 60)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @Column(name = "qtdFood")
    @Min(0)
    private long qtdFood;

    @Column(name = "qtdOil")
    @Min(0)
    private long qtdOil;

    @Column(name = "qtdIron")
    @Min(0)
    private long qtdIron;

    @Column(name = "qtdMinerals")
    @Min(0)
    private long qtdMinerals;

    @Column(name = "qtdUranium")
    @Min(0)
    private int qtdUranium;

    private String troopType;

    private long recruitingSpeed;

    private int woundedCapacity;

    private int healingSpeed;

    private int fleetLimitBonus;

}
