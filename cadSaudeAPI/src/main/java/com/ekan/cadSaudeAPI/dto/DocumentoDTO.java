package com.ekan.cadSaudeAPI.dto;

import com.ekan.cadSaudeAPI.enums.TipoDocumento;
import com.ekan.cadSaudeAPI.model.Beneficiario;
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
    private Long beneficiario_id;

    public static DocumentoDTO converterDoc(Documento doc) {
        DocumentoDTO dto = new DocumentoDTO();
        dto.setId(doc.getId());
        dto.setDescricao(doc.getDescricao());
        dto.setTipoDocumento(doc.getTipoDocumento());
        dto.setBeneficiario_id(doc.getBeneficiario().getId());
        return dto;
    }

}
