package br.com.zup.edu.dp.api.controller;

import br.com.zup.edu.dp.api.model.Cargo;
import br.com.zup.edu.dp.api.model.Zupper;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ZupperRequest {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private Cargo cargo;

    @NotNull
    @Size(min = 2)
    private List<@Valid EnderecoRequest> enderecos;

    public ZupperRequest(String nome, String email, Cargo cargo, List<EnderecoRequest> enderecos) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.enderecos = enderecos;
    }

    public Zupper paraZupper() {
        Zupper zupper = new Zupper(nome,email,cargo);

        this.enderecos.forEach(er -> {
            zupper.adicionaEndereco(er.paraEndereco());
        });

        return zupper;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public List<EnderecoRequest> getEnderecos() {
        return enderecos;
    }
}
