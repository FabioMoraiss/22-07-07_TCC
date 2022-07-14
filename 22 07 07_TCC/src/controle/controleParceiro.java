package controle;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import modelo.parceiro;
import persistencia.daoParceiro;

public class controleParceiro {
    private parceiro parceiro;
    private ArrayList<parceiro> listaParceiros;
    private daoParceiro daoparceiro;
    private boolean editarRegistro = false;

    public controleParceiro() {
        listaParceiros = new ArrayList<>();
        parceiro = new parceiro();
        daoparceiro = new daoParceiro();
    }

    // GETTERS AND SETTERS START 🎈

    public parceiro getParceiro() {
        return this.parceiro;
    }

    public void setParceiro(parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public ArrayList<parceiro> getListaParceiros() {
        return this.listaParceiros;
    }

    public void setListaParceiros(ArrayList<parceiro> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }

    public daoParceiro getDaoparceiro() {
        return this.daoparceiro;
    }

    public void setDaoparceiro(daoParceiro daoparceiro) {
        this.daoparceiro = daoparceiro;
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

    // GETTERS AND SETTERS END 🎈

    public void carregarParceiro(int idparce) {
        parceiro = daoparceiro.carregarParceiroEspecifico(idparce);
    }

    public boolean salvar() {
        if(this.editarRegistro == true) {
            return daoparceiro.atualizarParceiro(parceiro);
        }
        return daoparceiro.registrarParceiro(parceiro);
    }

    public boolean deletar(parceiro idparce) {
        return daoparceiro.removerParceiro(idparce);
    }

    public void carregarparceiros() {
        listaParceiros = daoparceiro.carregarTodosParceiros();
    }

    public DefaultTableModel gerarDefaultTableModel() {
        carregarparceiros();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("nome fantasia");
        model.addColumn("razao social");
        model.addColumn("cnpj");
        model.addColumn("email");
        model.addColumn("telefone");

        for (int i = 0; i<listaParceiros.size(); i++) {
            parceiro parc = listaParceiros.get(i);
            Object[] dados = {
                parc.getId(),
                parc.getNome_fantasia(),
                parc.getRazao_social(),
                parc.getCnpj(),
                parc.getEmail(),
                parc.getTelefone(),
            };
            model.addRow(dados);
        }
        return model;
    }
    
}
