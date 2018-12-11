package com.jpraphael.marble.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "ITENS_ORCAMENTOS")
@SequenceGenerator(name = "ITENS_ORCAMENTOS_SEQ", sequenceName = "ITENS_ORCAMENTOS_SEQ", allocationSize = 1)
public class ItemOrcamento implements Entidade {

    @Id
    @Column(name = "ID_ITEM_ORCAMENTO")
    @GeneratedValue(generator = "ITENS_ORCAMENTOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_PRODUTO")
    @NotNull(message = "O produto é obrigatório")
    private Produto produto;

    @Column(name = "QTD_PRODUTO")
    private Long qtdProduto;

    @Column(name = "VALOR_TOTAL")
    private Long valorTotal;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQtdProduto() {
        return qtdProduto;
    }

    public void setQtdProduto(Long qtdProduto) {
        this.qtdProduto = qtdProduto;
    }

    public Long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
