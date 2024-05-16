package com.postech.gestaodeenvio.repository;

import com.postech.gestaodeenvio.entities.Envio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepository extends JpaRepository<Envio, Long> {
}
