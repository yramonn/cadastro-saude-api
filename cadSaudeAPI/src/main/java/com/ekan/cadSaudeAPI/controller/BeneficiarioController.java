package com.ekan.cadSaudeAPI.controller;

import com.ekan.cadSaudeAPI.dto.BeneficiarioDTO;
import com.ekan.cadSaudeAPI.dto.DocumentoDTO;
import com.ekan.cadSaudeAPI.model.Beneficiario;
import com.ekan.cadSaudeAPI.model.Documento;
import com.ekan.cadSaudeAPI.repository.BeneficiarioRepository;
import com.ekan.cadSaudeAPI.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/beneficiarios")

public class BeneficiarioController {

    @Autowired
    BeneficiarioService beneficiarioService;

    @Autowired
    BeneficiarioRepository beneficiarioRepository;


    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar um beneficiário junto com seus documentos")
    public ResponseEntity<String> cadastrarBenefComDocumentos(@RequestBody BeneficiarioDTO beneficiarioDTO) {
        try {
            Beneficiario beneficiario = beneficiarioService.cadastrarBeneficiarioComDocumentos(beneficiarioDTO);
            return new ResponseEntity<>("Beneficiário cadastrado com sucesso. ID: " + beneficiario.getId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Listar todos os beneficiários cadastrados")
    @GetMapping("/listar")
    public ResponseEntity<List<Beneficiario>> listarBenef() {
        List<Beneficiario> beneficiarios = beneficiarioService.listarBeneficiarios();
        if(beneficiarios == null || beneficiarios.isEmpty())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<>(beneficiarios, HttpStatus.OK);
    }

    @GetMapping("/{id}/documentos")
    @Operation(summary = "Listar todos os documentos de um beneficiário a partir de seu id")
    public ResponseEntity<?> listarDocumentosPorBenef(@PathVariable Long id) {
        try {
            Documento doc = beneficiarioService.listarDocumentosBenef(id);

            if (doc == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario não encontrada para o ID: " + id);
            }

            return ResponseEntity.ok(DocumentoDTO.converterDoc(doc));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Usuario não encontrada para o ID");
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar os dados cadastrais de um beneficiário")
    public ResponseEntity<?> atualizarBenef(@PathVariable Long id, @RequestBody BeneficiarioDTO dto) {
        try {
            Beneficiario benefExistente = beneficiarioService.getBenefById(id);
            if (benefExistente == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Benef não encontrado para o ID: " + id);
            }

            benefExistente.setNome(dto.getNome());
            benefExistente.setTelefone(dto.getTelefone());
            benefExistente.setDataNascimento(dto.getDataNascimento());
            benefExistente.setDataAtualizacao(new Date());

            beneficiarioService.validarBenef(benefExistente);
            beneficiarioRepository.save(benefExistente);

            return ResponseEntity.ok("Benef atualizado com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar Benef para o ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover um beneficiário")
    public ResponseEntity<String> deleteBeneficiario(@PathVariable Long id) {
        try {
            beneficiarioService.excluirBeneficiario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Benef deletada com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao deletar benef, o ID não existe!");
        }
    }
}
