package com.ekan.cadSaudeAPI.model;

import com.ekan.cadSaudeAPI.enums.TipoDocumento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
@Table(name = "tb_documento")
@Data
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    @NotNull
    @Column(nullable = false)
    private String descricao;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataInclusao;

    @Temporal(TemporalType.DATE)
    private Date dataAtualizacao;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "beneficiario_id",  nullable = false)
    @JsonIgnore
    private Beneficiario beneficiario;

    public void setDataAtualizacaoVazia() {
        this.dataAtualizacao = null;
    }
}
