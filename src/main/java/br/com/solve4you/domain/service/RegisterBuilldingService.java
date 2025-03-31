package br.com.solve4you.domain.service;

import br.com.solve4you.domain.model.Building;
import br.com.solve4you.domain.repository.BuildingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegisterBuilldingService {
    private final BuildingRepository buildingRepository;

    @Transactional
    public Building adicionar(Building building){
        return buildingRepository.save(building);
    }
    @Transactional
    public void deletar(int buildingId){
        buildingRepository.deleteById(buildingId);
    }
}
