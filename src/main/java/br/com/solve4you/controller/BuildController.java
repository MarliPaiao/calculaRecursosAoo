package br.com.solve4you.controller;

import br.com.solve4you.domain.exception.CustomException;
import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import br.com.solve4you.domain.service.BuildingResourcesCalculator;
import br.com.solve4you.domain.service.RegisterBuilldingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/buildings")
public class BuildController {

    @Autowired
    private final BuildingRepository buildingRepository;
    private final RegisterBuilldingService registerBuilldingService;
    private final BuildingResourcesCalculator buildingResourcesCalculator;

    @GetMapping
    public List<Building> list(){
        return buildingRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Building add(@Valid @RequestBody Building building){
        boolean existBuilding = buildingRepository.findByStageAndName(building.getStage(), building.getName())
                .filter(b -> !b.equals(building))
                .isPresent();
        if(existBuilding){
            throw new CustomException("This building is already included in the database");
        }
        return registerBuilldingService.adicionar(building);
    }

   @PostMapping("/calculate-difference")
     public ResponseEntity<Building> calculateResourceDifference(@Valid @RequestBody Building currentBuilding,
                                                                 @RequestParam @Positive int desiredStage) {

         try {
             Building resourceDifference = buildingResourcesCalculator.calculateResourceDifference(currentBuilding, desiredStage);
             if (resourceDifference == null) {
                 throw new CustomException("The request must be properly filled out with the current and final stages of the building.");
             }
             return ResponseEntity.ok(resourceDifference);
         } catch (Exception e) {
             throw new CustomException("An error occurred while calculating the resource difference: " + e.getMessage());
         }
     }

    @GetMapping("/{buildingId}")
    public ResponseEntity<Building> searchBuilging(@PathVariable int buildingId) {
        return buildingRepository.findById(buildingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{buildingId}")
    public ResponseEntity<Building> buildUpdate(@PathVariable int buildingId,
                                                @RequestBody Building building){
        if (!buildingRepository.existsById(buildingId)){
            return ResponseEntity.notFound().build();
        }
        building.setId(buildingId);
        Building buildingUpdate = registerBuilldingService.adicionar(building);

        return ResponseEntity.ok(buildingUpdate);
    }

    @DeleteMapping("/{buildingId}")
    public ResponseEntity<Void> buildingDelete(@PathVariable int buildingId){
        if (!buildingRepository.existsById(buildingId)){
            return ResponseEntity.notFound().build();
        }
        registerBuilldingService.deletar(buildingId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> catchException(CustomException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
