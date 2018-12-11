package com.jpraphael.marble.model;

import com.jpraphael.marble.utils.RequestAuth;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@RequestAuth
@Table(name = "CLIENTES")
@SequenceGenerator(name = "CLIENTES_SEQ", sequenceName = "CLIENTES_SEQ", allocationSize = 1)
public class Cliente implements Entidade {

    @Id
    @Column(name = "ID_CLIENTE")
    @GeneratedValue(generator = "CLIENTES_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "DOCUMENTO", length = 20)
    @NotNull(message = "{Cliente.documento.NotNull}")
    @Size(min = 5, max = 18, message = "{Cliente.documento.Size}")
    private String documento;

    @Column(name = "NOME", length = 80)
    @NotNull(message = "{Cliente.nome.NotNull}")
    @Size(min = 5, max = 80, message = "{Cliente.nome.Size}")
    private String nome;

    @Size(min = 8, max = 20, message = "{Cliente.telefone.Size}")
    @Column(name = "TELEFONE", length = 20)
    private String telefone;

    @Email(message = "{Cliente.email.Email}")
    @NotNull(message = "{Cliente.email.NotNull}")
    @Size(max = 100, message = "{Cliente.email.Size}")
    @Column(name = "EMAIL", length = 100)
    private String email;

    @Size(max = 300, message = "{Cliente.endereco.Size}")
    @Column(name = "ENDERECO", length = 300)
    private String endereco;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.documento);
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + Objects.hashCode(this.telefone);
        hash = 83 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.documento, other.documento)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", documento=" + documento + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + '}';
    }
}
