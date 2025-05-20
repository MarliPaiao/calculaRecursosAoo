package br.com.solve4you.domain.service;

import br.com.solve4you.domain.exception.CustomException;
import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import br.com.solve4you.exceptionHandler.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ValidationService {
    private BuildingRepository buildingRepository;
    /**
     * Validates if a building with the same stage and name already exists in the database.
     *
     * @param building The building to validate.
     * @throws CustomException if a building with the same stage and name already exists.
     */
    public void validateBuilding(Building building){
        boolean existBuilding = buildingRepository.findByStageAndName(building.getStage(), building.getName())
                .filter(b -> !b.equals(building))
                .isPresent();
        if(existBuilding){
            throw new CustomException("This building is already included in the database");
        }
    }
    /**
     * Validates if the desired stage is greater than the current stage of the building.
     *
     * @param currentBuilding The current building.
     * @param desiredStage    The desired stage to validate.
     * @throws CustomException if the desired stage is not greater than the current stage.
     */
    public void validateStageBuilding(Building currentBuilding, int desiredStage) {
        if (currentBuilding.getStage() >= desiredStage) {
            throw new CustomException("The desired stage must be greater than the current stage.");
        }
    }
    /**
     * Validates if a building with the given ID exists in the database.
     *
     * @param buildingId The ID of the building to validate.
     * @throws CustomException if the building does not exist in the database.
     */
    public void validateNoExistingBuilding(int buildingId) {
        if (!buildingRepository.existsById(buildingId)){
            throw new EntityNotFoundException("This building does not exist in the database");
        }
    }
}
