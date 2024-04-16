package com.postech.crmservice.controllers;

import com.postech.crmservice.entities.DTOs.ClienteDto;
import com.postech.crmservice.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;



    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @PostMapping
    public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto) {
        clienteService.save(clienteDto);
        return ResponseEntity.ok(clienteDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Long id,@RequestBody ClienteDto clienteDto) {
        clienteService.update(id, clienteDto);
        return ResponseEntity.ok(clienteDto);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody ClienteDto clienteDto) {
        clienteService.delete(clienteDto.id());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getById(@PathVariable Long id) {
        ClienteDto clienteDto = clienteService.getById(id);
        return ResponseEntity.ok(clienteDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll() {
        List<ClienteDto> clienteDtos = clienteService.getAll();
        return ResponseEntity.ok(clienteDtos);
    }
}
