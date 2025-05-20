package br.com.solve4you.controller;

import br.com.solve4you.domain.dto.ResourceCalculatorResponse;
import br.com.solve4you.domain.exception.CustomException;
import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import br.com.solve4you.domain.service.ResourcesCalculatorService;
import br.com.solve4you.domain.service.RegisterBuilldingService;
import br.com.solve4you.domain.service.ValidationService;
import br.com.solve4you.exceptionHandler.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Building Controler",
        description = "Endpoints for managing the resources to construct buildings in the game Age of Origins.")
@AllArgsConstructor
@RestController
@RequestMapping("/buildings")
public class BuildController {

    @Autowired
    private final BuildingRepository buildingRepository;
    private final RegisterBuilldingService registerBuilldingService;
    private final ResourcesCalculatorService resourcesCalculatorService;
    private final ValidationService validationService;

    @Operation(summary = "List all buildings",
            description = "Returns a list of all buildings in the database.")
    @GetMapping
    public List<Building> list(){
        return buildingRepository.findAll();
    }

    @Operation(summary = "Add a new building",
            description = "Adds a new building to the database.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Building add(@Valid @RequestBody Building building){
        validationService.validateBuilding(building);
        return registerBuilldingService.adicionar(building);
    }
    @Operation(summary = "Calculate resource difference an upgrade and the pre requirement",
            description = "Calculates the difference in resources needed to upgrade a building to a desired stage.")
    @PostMapping("/calculate-difference")
     public ResponseEntity<ResourceCalculatorResponse> buildingRequirements(@Valid @RequestBody Building currentBuilding,
                                                                 @RequestParam @Positive int desiredStage) {
        validationService.validateStageBuilding(currentBuilding, desiredStage);
        ResourceCalculatorResponse resourceDifference =
                resourcesCalculatorService.buildingRequirements(currentBuilding, desiredStage);
        return ResponseEntity.ok(resourceDifference);
     }

    @Operation(summary = "Search for a building by ID",
            description = "Returns a building by its ID.")
    @GetMapping("/{buildingId}")
    public ResponseEntity<Building> searchBuilding(@PathVariable int buildingId) {
        return buildingRepository.findByIdWithBuildingRequirements(buildingId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Building not found in the database"));
    }

    @Operation(summary = "Update a building",
            description = "Updates a building in the database.")
    @PutMapping("/{buildingId}")
    public ResponseEntity<Building> buildUpdate(@PathVariable int buildingId,
                                                @RequestBody Building building){
        validationService.validateNoExistingBuilding(buildingId);
        building.setId(buildingId);
        Building buildingUpdate = registerBuilldingService.adicionar(building);
        return ResponseEntity.ok(buildingUpdate);
    }
    @Operation(summary = "Delete a building",
            description = "Deletes a building from the database.")
    @DeleteMapping("/{buildingId}")
    public ResponseEntity<Void> buildingDelete(@PathVariable int buildingId){
        validationService.validateNoExistingBuilding(buildingId);
        registerBuilldingService.deletar(buildingId);
        return ResponseEntity.noContent().build();
    }
}
