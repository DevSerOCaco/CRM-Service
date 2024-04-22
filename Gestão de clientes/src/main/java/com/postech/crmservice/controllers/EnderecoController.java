package com.postech.crmservice.controllers;

import com.postech.crmservice.entities.DTOs.EnderecoDto;
import com.postech.crmservice.services.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enderecos")
public class EnderecoController {
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<EnderecoDto> save(@RequestBody EnderecoDto enderecoDto) {
        enderecoService.save(enderecoDto);
        return ResponseEntity.ok(enderecoDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoDto> update(@PathVariable Long id, @RequestBody EnderecoDto enderecoDto) {
        enderecoService.update(id, enderecoDto);
        return ResponseEntity.ok(enderecoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> getById(@PathVariable Long id) {
        EnderecoDto enderecoDto = enderecoService.getById(id);
        return ResponseEntity.ok(enderecoDto);
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDto>> getAll() {
        List<EnderecoDto> enderecoDtos = enderecoService.getAll();
        return ResponseEntity.ok(enderecoDtos);
    }
}
