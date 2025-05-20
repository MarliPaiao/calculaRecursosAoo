package br.com.solve4you.domain.dto;

import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.model.BuildingType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Getter
@Setter
public class ResourceCalculatorResponse {

    private BuildingType buildingType;
    private long qtdFood;
    private long qtdOil;
    private long qtdIron;
    private long qtdMinerals;
    private long qtdUranium;
    private List<Building> buildingRequirements;
}
