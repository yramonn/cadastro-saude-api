package com.ekan.cadSaudeAPI.service.impl;

import com.ekan.cadSaudeAPI.enums.TipoDocumento;
import com.ekan.cadSaudeAPI.exception.RegraNegocioException;
import com.ekan.cadSaudeAPI.model.Documento;
import com.ekan.cadSaudeAPI.service.DocumentoService;
import org.springframework.stereotype.Service;

@Service
public class DocumentoServiceImpl implements DocumentoService {
    @Override
    public void validarDoc(Documento doc) {

        if (doc.getTipoDocumento() == null ||
                (doc.getTipoDocumento() != TipoDocumento.CNPJ && doc.getTipoDocumento() != TipoDocumento.CPF)) {
            throw new RegraNegocioException("Informe um tipo de documento válido (CNPJ ou CPF).");
        }

        if (doc.getDescricao() == null || doc.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("Informe á Descrição do Documento.");
        }

    }
}
