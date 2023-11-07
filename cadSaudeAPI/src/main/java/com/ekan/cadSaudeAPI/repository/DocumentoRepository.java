package com.ekan.cadSaudeAPI.repository;

import com.ekan.cadSaudeAPI.model.Beneficiario;
import com.ekan.cadSaudeAPI.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    List<Documento> findByBeneficiario(Beneficiario beneficiario);


}
