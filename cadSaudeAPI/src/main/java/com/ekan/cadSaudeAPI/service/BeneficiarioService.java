package com.ekan.cadSaudeAPI.service;

import com.ekan.cadSaudeAPI.dto.BeneficiarioDTO;
import com.ekan.cadSaudeAPI.dto.DocumentoDTO;
import com.ekan.cadSaudeAPI.model.Beneficiario;
import com.ekan.cadSaudeAPI.model.Documento;

import java.util.List;

public interface BeneficiarioService {

    Beneficiario cadastrarBeneficiarioComDocumentos(BeneficiarioDTO beneficiarioDTO);

    List<Beneficiario> listarBeneficiarios();

    Documento listarDocumentosBenef(Long id);

    Beneficiario getBenefById(Long id);

    void excluirBeneficiario(Long id);

    void validarBenef(Beneficiario benef);


}
