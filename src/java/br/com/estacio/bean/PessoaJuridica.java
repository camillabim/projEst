/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.estacio.bean;

import br.com.estacio.util.ValidacaoException;

/**
 *
 * @author Camilla Bim <camillabim@hotmail.com.br>
 */
public class PessoaJuridica extends Pessoa {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    
    

    public PessoaJuridica() {
    }

    //validação personalizada
    public void valida() throws ValidacaoException {
        if (getNomeFantasia() == null || getNomeFantasia().equals("")) {
            throw new ValidacaoException("O preenchimento do campo Nome Fantasia é obrigatório");
        }
        if (getRazaoSocial() == null || getRazaoSocial().equals("")) {
            throw new ValidacaoException("O preenchimento do campo Razão Social é obrigatório");
        }
        if (getCnpj() == null || getCnpj().equals("")) {
            throw new ValidacaoException("O preenchimento do campo CNPJ é obrigatório");
        }
    }

    public PessoaJuridica(String nomeFantasia, String razaoSocial, String cnpj, String inscricaoEstadual, String inscricaoMunicipal) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public PessoaJuridica(int codigo, String nomeFantasia, String razaoSocial, String cnpj, String inscricaoEstadual, String inscricaoMunicipal) {
        super(codigo);
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    /**
     * @return the nomeFantasia
     */
    public String getNomeFantasia() {
        return nomeFantasia;
    }

    /**
     * @param nomeFantasia the nomeFantasia to set
     */
    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    /**
     * @return the razaoSocial
     */
    public String getRazaoSocial() {
        return razaoSocial;
    }

    /**
     * @param razaoSocial the razaoSocial to set
     */
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the inscricaoEstadual
     */
    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    /**
     * @param inscricaoEstadual the inscricaoEstadual to set
     */
    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    /**
     * @return the inscricaoMunicipal
     */
    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    /**
     * @param inscricaoMunicipal the inscricaoMunicipal to set
     */
    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    
    
    
}
