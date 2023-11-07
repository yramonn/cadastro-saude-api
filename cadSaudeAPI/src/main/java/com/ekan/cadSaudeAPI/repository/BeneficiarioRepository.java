package com.ekan.cadSaudeAPI.repository;

import com.ekan.cadSaudeAPI.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
}
