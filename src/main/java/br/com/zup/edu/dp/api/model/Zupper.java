package br.com.zup.edu.dp.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Zupper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Cargo cargo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zupper")
    private List<Endereco> enderecos = new ArrayList<>();


    /**
     * @deprecated Construtor para uso exclusivo do Hibernate.
     */
    @Deprecated
    public Zupper() {
    }

    public Zupper(String nome, String email, Cargo cargo) {
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
    }

    public void adicionaEndereco(Endereco novoEndereco) {
        novoEndereco.setZupper(this);
        this.enderecos.add(novoEndereco);
    }

    public Long getId() {
        return id;
    }

    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }
}
