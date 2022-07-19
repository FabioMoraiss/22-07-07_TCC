package controle;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import modelo.folha_aluguel;
import modelo.contrato;
import persistencia.daoFolha_aluguel;
import persistencia.daoContrato;

public class controleFolha_aluguel {
    private folha_aluguel folha_aluguel;
    private ArrayList<folha_aluguel> listaDividas;
    private daoFolha_aluguel daoFolha_aluguel;
    private boolean editarRegistro = false;
    private daoContrato daoContrato;
    private ArrayList<contrato> listarContratos;

    public controleFolha_aluguel() {
        listaDividas = new ArrayList<>();
        folha_aluguel = new folha_aluguel();
        daoFolha_aluguel = new daoFolha_aluguel();
        daoContrato = new daoContrato();
        listarContratos = daoContrato.carregarTodosContratos();
    }

    // GETTERS AND SETTERS START 🎈

    public folha_aluguel getFolha_aluguel() {
        return this.folha_aluguel;
    }

    public void setFolha_aluguel(folha_aluguel folha_aluguel) {
        this.folha_aluguel = folha_aluguel;
    }

    public ArrayList<folha_aluguel> getListaDividas() {
        return this.listaDividas;
    }

    public void setListaDividas(ArrayList<folha_aluguel> listaDividas) {
        this.listaDividas = listaDividas;
    }

    public daoFolha_aluguel getDaoFolha_aluguel() {
        return this.daoFolha_aluguel;
    }

    public void setDaoFolha_aluguel(daoFolha_aluguel daoFolha_aluguel) {
        this.daoFolha_aluguel = daoFolha_aluguel;
    }

    public boolean isEditarRegistro() {
        return this.editarRegistro;
    }

    public boolean getEditarRegistro() {
        return this.editarRegistro;
    }

    public void setEditarRegistro(boolean editarRegistro) {
        this.editarRegistro = editarRegistro;
    }

    public daoContrato getDaoContrato() {
        return this.daoContrato;
    }

    public void setDaoContrato(daoContrato daoContrato) {
        this.daoContrato = daoContrato;
    }

    public ArrayList<contrato> getListarContratos() {
        return this.listarContratos;
    }

    public void setListarContratos(ArrayList<contrato> listarContratos) {
        this.listarContratos = listarContratos;
    }

   
    // GETTERS AND SETTERS END 🎈

    public void carregarDivida(int iddivida) {
        folha_aluguel = daoFolha_aluguel.carregarDividaEspecifica(iddivida);
    }

    public boolean salvar() {
        if(this.editarRegistro == true) {
            return daoFolha_aluguel.atualizarDivida(folha_aluguel);
        } else {
            return daoFolha_aluguel.registarDivida(folha_aluguel);
        }
    }
    public boolean deletar(folha_aluguel idfol) {
        return daoFolha_aluguel.removerDivida(idfol) ;
    }

    public void carregarFolha() {
        listaDividas = daoFolha_aluguel.carregarTodasDividas();
    }

    public DefaultTableModel gerardDefaultTableModel() {
        carregarFolha();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("valor");
        model.addColumn("estado da divida");
        model.addColumn("descrição");
        model.addColumn("numero da parcela");
        model.addColumn("vencimento");
        model.addColumn("id do contrato");

        for (int i = 0; i<listaDividas.size(); i++) {
            folha_aluguel fol = listaDividas.get(i);
            Object[] dados = {
                fol.getId(),
                fol.getValor(),
                fol.estadoDaDivida(),
                fol.getDescricao(),
                fol.getNumero_parcela(),
                fol.getData_vencimento(),
                fol.getContrato().getId(),
            };
            model.addRow(dados);
       }
       return model;
    }
    public Integer[] exibirContratos() {
        Integer[] contratos = new Integer[listarContratos.size()+1];
        contratos[0] = null;
        for(int i = 0; i<listarContratos.size(); i++) {
            contratos[i+1] = listarContratos.get(i).getId();
        }
        return contratos;
    }

    
}
