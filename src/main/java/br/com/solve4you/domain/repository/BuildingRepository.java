package br.com.solve4you.domain.repository;

import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.model.BuildingType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Integer> {
    Optional<Building> findByStageAndName(int stage, String name);

    Optional<Building> findById(int id);

//    @EntityGraph(attributePaths = "buildingRequirements")
    @Query("SELECT b FROM Building b LEFT JOIN FETCH b.buildingRequirements WHERE b.id = :id")
    Optional<Building> findByIdWithBuildingRequirements(@Param("id") int id);

    List<Building> findByBuildingTypeAndStageBetween(BuildingType buildingType, int startStage, int endStage);

    List<Building> findByBuildingType(BuildingType buildingType);
}
