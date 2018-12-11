package com.jpraphael.marble.viewmodel;

import com.jpraphael.marble.model.ViewOrcamento;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

public class OrcamentoREL {
    private String path; //Caminho base

    private String pathToReportPackage; // Caminho para o package onde estão armazenados os relatorios Jarper

    //Recupera os caminhos para que a classe possa encontrar os relatórios
    public OrcamentoREL() {
        this.path = "C:\\Projetos\\FullStack2\\marble\\api\\";
        this.pathToReportPackage = path;
        System.out.println(path);
    }


    //Imprime/gera uma lista de Orcamento
    public void imprimir(List<ViewOrcamento> orcamentos, String arquivo) throws Exception {
            JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Orcamento.jrxml");

        JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(orcamentos));

        JasperExportManager.exportReportToPdfFile(print, "C:\\Relatorios\\"+arquivo+".pdf");
    }

    public String getPathToReportPackage() {
        return this.pathToReportPackage;
    }
}