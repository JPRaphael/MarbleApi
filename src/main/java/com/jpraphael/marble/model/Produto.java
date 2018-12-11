package com.jpraphael.marble.model;

import com.jpraphael.marble.utils.RequestAuth;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@RequestAuth
@Table(name = "PRODUTOS")
@SequenceGenerator(name = "PRODUTOS_SEQ", sequenceName = "PRODUTOS_SEQ", allocationSize = 1)
public class Produto implements Entidade {

    @Id
    @Column(name = "ID_PRODUTO")
    @GeneratedValue(generator = "PRODUTOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "{Produto.codigo.NotNull}")
    @Size(min = 1, max = 20, message = "{Produto.codigo.Size}")
    @Column(name = "CODIGO", length = 20, unique = true)
    private String codigo;

    @Size(max = 255, message = "{Produto.descricao.Size}")
    @Column(name = "DESCRICAO", length = 255)
    private String descricao;

    @NotNull(message = "{Produto.preco.NotNull}")
    @Digits(integer = 10, fraction = 5, message = "{Produto.preco.Digits}")
    @Column(name = "PRECO", precision = 15, scale = 5)
    private BigDecimal preco;

    @Column(name = "QUANTIDADE", precision = 15, scale = 5)
    private BigDecimal quantidade;

    @Column(name = "QUANTIDADE_PENDENTE", precision = 15, scale = 5)
    private Double quantidadePendente = 0D;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public Double getQuantidadePendente() {
        return quantidadePendente;
    }

    public void setQuantidadePendente(Double quantidadePendente) {
        this.quantidadePendente = quantidadePendente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) &&
                Objects.equals(codigo, produto.codigo) &&
                Objects.equals(descricao, produto.descricao) &&
                Objects.equals(preco, produto.preco) &&
                Objects.equals(quantidade, produto.quantidade) &&
                Objects.equals(quantidadePendente, produto.quantidadePendente);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, codigo, descricao, preco, quantidade, quantidadePendente);
    }
}
