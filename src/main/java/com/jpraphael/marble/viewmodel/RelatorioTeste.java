package com.jpraphael.marble.viewmodel;

import com.jpraphael.marble.model.ViewOrcamento;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RelatorioTeste {
    public static void main(String[] args) {
        try {
            List<ViewOrcamento> orcamentos = new ArrayList<>();

            List<Long> qtdPedra = new ArrayList<>();
            List<Long> valorPedra = new ArrayList<>();
            List<String> nomePedra = new ArrayList<>();
            List<ItemPedido> itens = new ArrayList<>();

            ItemPedido item = new ItemPedido(
                    "Granito Amarelo Capri",
                    2.8,
                    50.0);

            ItemPedido item2 = new ItemPedido(
                    "Granito Amarelo Capri22222",
                    12.80,
                    150.0);

            itens.add(item);
            itens.add(item2);

            nomePedra.add("Granito Amarelo Capri 2");
            nomePedra.add("Granito Amarelo Capri 3");
            nomePedra.add("Granito Amarelo Capri 4");
            nomePedra.add("Granito Amarelo Capri 5");

            qtdPedra.add(2L);
            qtdPedra.add(2L);
            qtdPedra.add(2L);
            qtdPedra.add(2L);
            qtdPedra.add(2L);

            valorPedra.add(45L);
            valorPedra.add(45L);
            valorPedra.add(45L);
            valorPedra.add(45L);
            valorPedra.add(45L);

            JRDataSource jrds = new JRBeanCollectionDataSource(itens);

            ViewOrcamento viewOrcamento1 = new ViewOrcamento();
            viewOrcamento1.setCliente("Gabriela");
            viewOrcamento1.setEnderecoCliente("Rua Pedro");
            viewOrcamento1.setTelefoneCliente("0499865261454");
            viewOrcamento1.setCidadeCliente("Criciuma");
            viewOrcamento1.setEmailCliente("joaopedroraphael@hotmail.com");
            viewOrcamento1.setDataVisita("01/01/2018");
            viewOrcamento1.setValorTotal(valorPedra.stream().mapToLong(a -> a).sum());
            viewOrcamento1.setItens((Collection<ItemPedido>) jrds);
            orcamentos.add(viewOrcamento1);

            OrcamentoREL relatorio = new OrcamentoREL();
            relatorio.imprimir(orcamentos, "RelatorioCompleto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}