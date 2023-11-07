package com.ekan.cadSaudeAPI.dto;

import com.ekan.cadSaudeAPI.enums.TipoDocumento;
import com.ekan.cadSaudeAPI.model.Documento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentoDTO {

    private Long id;
    private TipoDocumento tipoDocumento;
    private String descricao;
    private BeneficiarioDTO beneficiario;

    public static DocumentoDTO converterDoc(Documento doc) {
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(doc.getId());
        dto.setDescricao(doc.getDescricao());
        dto.setTipoDocumento(doc.getTipoDocumento());
        dto.setBeneficiario(doc.getBeneficiario().getId());
        return dto;
    }

    private void setBeneficiario(Long id) {
    }
}
