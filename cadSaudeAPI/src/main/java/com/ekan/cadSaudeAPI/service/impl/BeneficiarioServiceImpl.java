package com.ekan.cadSaudeAPI.service.impl;

import com.ekan.cadSaudeAPI.dto.BeneficiarioDTO;
import com.ekan.cadSaudeAPI.dto.DocumentoDTO;
import com.ekan.cadSaudeAPI.exception.RegraNegocioException;
import com.ekan.cadSaudeAPI.model.Beneficiario;
import com.ekan.cadSaudeAPI.model.Documento;
import com.ekan.cadSaudeAPI.repository.BeneficiarioRepository;
import com.ekan.cadSaudeAPI.repository.DocumentoRepository;
import com.ekan.cadSaudeAPI.service.BeneficiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class BeneficiarioServiceImpl implements BeneficiarioService {

    @Autowired
    BeneficiarioRepository beneficiarioRepository;

    @Autowired
    DocumentoRepository documentoRepository;

    @Autowired
    ModelMapperConfig modelMapper;


    public Beneficiario cadastrarBeneficiarioComDocumentos(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome(beneficiarioDTO.getNome());
        beneficiario.setTelefone(beneficiarioDTO.getTelefone());
        beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
        beneficiario.setDataInclusao(new Date());

        List<Documento> documentos = new ArrayList<>();
        for (DocumentoDTO documentoDTO : beneficiarioDTO.getDocumentos()) {
            Documento documento = new Documento();
            documento.setTipoDocumento(documentoDTO.getTipoDocumento());
            documento.setDescricao(documentoDTO.getDescricao());
            documento.setDataInclusao(new Date());
            documento.setBeneficiario(beneficiario);
            documentos.add(documento);
        }

        beneficiario.setDocumentos(documentos);


        return beneficiarioRepository.save(beneficiario);
    }

    @Override
    public List<Beneficiario> listarBeneficiarios() {
        return beneficiarioRepository.findAll();

    }

    @Override
    public Documento listarDocumentosBenef(Long id) {
        Optional<Documento> documentoBenef = documentoRepository.findById(id);
        if (documentoBenef.isEmpty()) {
            throw new RegraNegocioException("Documento não existe!");
        }
        return documentoBenef.get();
    }

    @Override
    public Beneficiario getBenefById(Long id) {
        Optional<Beneficiario> benefOptional = beneficiarioRepository.findById(id);
        if (benefOptional.isEmpty()) {
            throw new RegraNegocioException("Essee benef não existe!");
        }
        return benefOptional.get();
    }

    @Override
    public void excluirBeneficiario(Long id) {
        Beneficiario benef = beneficiarioRepository.findById(id).orElseThrow(() ->
                new RegraNegocioException("Benef não encontrada para o ID: " + id));
        beneficiarioRepository.delete(benef);
    }

    @Override
    public void validarBenef(Beneficiario benef) {
        if (benef.getNome() == null || benef.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("Informe seu Nome");
        }

        if (benef.getTelefone() == null) {
            throw new RegraNegocioException("Telefone/Celular Vazio");
        }

        if (benef.getTelefone().charAt(0) == '9') {
            if(benef.getTelefone().length() != 10) {
                throw new RegraNegocioException("Formato para celular: (90000-0000)");
            }
        }

        if (benef.getTelefone().length() != 9) {
            throw new RegraNegocioException("Formato para celular: (XXX-XXXX)");
        }

        if (benef.getDataNascimento() == null || benef.getDataNascimento().equals("")) {
            throw new RegraNegocioException("Informe sua  Data de Nascimento no Formato: yyyy-mm-dd");
        }

    }


}
