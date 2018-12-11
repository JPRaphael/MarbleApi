package com.jpraphael.marble.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USUARIOS")
@SequenceGenerator(name = "USUARIOS_SEQ", sequenceName = "USUARIOS_SEQ", allocationSize = 1)
public class Usuario implements Entidade {

    @Id
    @Column(name = "ID_USUARIO")
    @GeneratedValue(generator = "USUARIOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "USUARIO", unique = true)
    private String usuario;

    @NotNull
    @Size(min = 5)
    @Column(name = "NOME", length = 80)
    private String nome;

    @NotNull
    @Size(min = 5)
    @Column(name = "EMAIL", unique = true, length = 150)
    private String email;

    @NotNull
    @Size(min = 5)
    @Column(name = "SENHA", length = 80)
    private String senha;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
