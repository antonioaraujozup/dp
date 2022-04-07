package br.com.zup.edu.dp.api.controller;

import br.com.zup.edu.dp.api.model.Endereco;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class EnderecoRequest {

    @NotBlank
    private String logradouro;

    @NotNull
    @Positive
    private Integer numero;

    @NotBlank
    private String bairro;

    private String complemento;

    @NotBlank
    private String cidade;

    @NotBlank
    private String estado;

    @NotBlank
    private String cep;

    public EnderecoRequest(String logradouro, Integer numero, String bairro, String complemento, String cidade, String estado, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.complemento = complemento;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    public Endereco paraEndereco() {
        return new Endereco(logradouro,numero,bairro,complemento,cidade,estado,cep);
    }

    public String getLogradouro() {
        return logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }
}
