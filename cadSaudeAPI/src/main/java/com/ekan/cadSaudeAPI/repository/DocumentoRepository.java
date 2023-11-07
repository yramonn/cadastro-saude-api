package com.ekan.cadSaudeAPI.repository;

import com.ekan.cadSaudeAPI.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
