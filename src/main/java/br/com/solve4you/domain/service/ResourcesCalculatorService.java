package br.com.solve4you.domain.service;

import br.com.solve4you.domain.dto.ResourceCalculatorResponse;
import br.com.solve4you.domain.exception.CustomException;
import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    @AllArgsConstructor
    @Service
    public class ResourcesCalculatorService {
        private final BuildingRepository buildingRepository;
        private final ValidationService validationService;


        public ResourceCalculatorResponse buildingRequirements(Building currentBuilding, int desiredStage) {
            validationService.validateStageBuilding(currentBuilding, desiredStage);

            Building buildingWithRequirements = buildingRepository
                    .findByIdWithBuildingRequirements(currentBuilding.getId())
                    .orElseThrow(() -> new CustomException("Building not found"));

            List<Building> stages = buildingRepository.findByBuildingTypeAndStageBetween(
                    currentBuilding.getBuildingType(), currentBuilding.getStage() + 1, desiredStage);

            ResourceCalculatorResponse response = new ResourceCalculatorResponse();
            response.setBuildingType(currentBuilding.getBuildingType());
            response.setBuildingRequirements(buildingWithRequirements.getBuildingRequirements());

            for (Building stage : stages) {
                response.setQtdFood(response.getQtdFood() + stage.getQtdFood());
                response.setQtdOil(response.getQtdOil() + stage.getQtdOil());
                response.setQtdIron(response.getQtdIron() + stage.getQtdIron());
                response.setQtdMinerals(response.getQtdMinerals() + stage.getQtdMinerals());
                response.setQtdUranium(response.getQtdUranium() + stage.getQtdUranium());
            }

            return response;
        }
    }


