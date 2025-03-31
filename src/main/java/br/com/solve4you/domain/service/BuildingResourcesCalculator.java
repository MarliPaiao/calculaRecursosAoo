package br.com.solve4you.domain.service;

import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

    @AllArgsConstructor
    @Service
    public class BuildingResourcesCalculator {
        private final BuildingRepository buildingRepository;


        public Building calculateResourceDifference(Building currentBuilding, int desiredStage) {
            if (currentBuilding.getStage() >= desiredStage) {
                throw new IllegalArgumentException("O stage desejado deve ser maior que o stage atual.");
            }

            List<Building> stages = buildingRepository.findByBuildingTypeAndStageBetween(
                    currentBuilding.getBuildingType(), currentBuilding.getStage() + 1, desiredStage);

            Building totalDifference = new Building();
            totalDifference.setBuildingType(currentBuilding.getBuildingType());

            for (Building stage : stages) {
                totalDifference.setQtdFood(totalDifference.getQtdFood() + stage.getQtdFood());
                totalDifference.setQtdOil(totalDifference.getQtdOil() + stage.getQtdOil());
                totalDifference.setQtdIron(totalDifference.getQtdIron() + stage.getQtdIron());
                totalDifference.setQtdMinerals(totalDifference.getQtdMinerals() + stage.getQtdMinerals());
                totalDifference.setQtdUranium(totalDifference.getQtdUranium() + stage.getQtdUranium());
            }

            return totalDifference;
        }
    }


