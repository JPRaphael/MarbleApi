package com.jpraphael.marble.model;

import com.jpraphael.marble.utils.RequestAuth;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@RequestAuth
@Table(name = "ORCAMENTOS")
@SequenceGenerator(name = "ORCAMENTOS_SEQ", sequenceName = "ORCAMENTOS_SEQ", allocationSize = 1)
public class Orcamento implements Entidade {

    @Id
    @Column(name = "ID_ORCAMENTO")
    @GeneratedValue(generator = "ORCAMENTOS_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    @NotNull(message = "{Cliente.documento.NotNull}")
    private Cliente cliente;

    @OneToMany
    @JoinColumn(name = "ID_ITEM_ORCAMENTO")
    @NotNull(message = "Adicione ao menos um item")
    private Collection<ItemOrcamento> itemOrcamento;

    @Override
    public Long getId() {
        return id;
    }

    @Column(name = "DATA_VISITA")
    private LocalDate dataVisita;

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public LocalDate getDataVisita() {
        return dataVisita;
    }

    public void setDataVisita(LocalDate dataVisita) {
        this.dataVisita = dataVisita;
    }

    public Collection<ItemOrcamento> getItemOrcamento() {
        return itemOrcamento;
    }

    public void setItemOrcamento(Collection<ItemOrcamento> itemOrcamento) {
        this.itemOrcamento = itemOrcamento;
    }
}
