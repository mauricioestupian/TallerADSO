package com.taller1.taller1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller1.taller1.dtos.CargoDto;
import com.taller1.taller1.models.Cargo;
import com.taller1.taller1.repositoryes.CargoRepository;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    private CargoRepository cargoRepo;

    @GetMapping("/cosa")
    public List<CargoDto> getcargos() {
        return cargoRepo.findAll().stream().map(cargo -> {
            CargoDto dto = new CargoDto();
            dto.setId(cargo.getId());
            dto.setCargo(cargo.getCargo());
            return dto;
        }).toList();
    }

    @GetMapping
    public List<Cargo> getMethodName() {
        return cargoRepo.findAll();
    }

}
