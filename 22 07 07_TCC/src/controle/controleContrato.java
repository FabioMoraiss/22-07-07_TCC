package controle;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import modelo.contrato;
import modelo.parceiro;
import modelo.espaco;
import persistencia.daoContrato;
import persistencia.daoParceiro;
import persistencia.daoEspaco;

import javax.swing.JOptionPane;


public class controleContrato {
    private contrato contrato;
    private parceiro parceiro;
    private espaco espaco;
    private ArrayList<contrato> listaContratos;
    private daoContrato daocontrato;
    private boolean editarRegistro = false;
    private daoParceiro daoParceiro;
    private daoEspaco daoEspaco;
    private ArrayList<parceiro> listarparceiros;
    private ArrayList<espaco> listarespacos;

    public controleContrato() {
        listaContratos = new ArrayList<>();
        contrato = new contrato();
        parceiro = new parceiro();
        espaco = new espaco();
        daocontrato = new daoContrato();
        daoParceiro = new daoParceiro();
        daoEspaco = new daoEspaco();
        listarparceiros = daoParceiro.carregarTodosParceiros();
        listarespacos = daoEspaco.carregarTodosEspacos();
    }
    // GETTERS AND SETTERS START 🎈


    public contrato getContrato() {
        return this.contrato;
    }

    public void setContrato(contrato contrato) {
        this.contrato = contrato;
    }

    public parceiro getParceiro() {
        return this.parceiro;
    }

    public void setParceiro(parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public espaco getEspaco() {
        return this.espaco;
    }

    public void setEspaco(espaco espaco) {
        this.espaco = espaco;
    }

    public ArrayList<contrato> getListaContratos() {
        return this.listaContratos;
    }

    public void setListaContratos(ArrayList<contrato> listaContratos) {
        this.listaContratos = listaContratos;
    }

    public daoContrato getDaocontrato() {
        return this.daocontrato;
    }

    public void setDaocontrato(daoContrato daocontrato) {
        this.daocontrato = daocontrato;
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

    public daoParceiro getDaoParceiro() {
        return this.daoParceiro;
    }

    public void setDaoParceiro(daoParceiro daoParceiro) {
        this.daoParceiro = daoParceiro;
    }

    public daoEspaco getDaoEspaco() {
        return this.daoEspaco;
    }

    public void setDaoEspaco(daoEspaco daoEspaco) {
        this.daoEspaco = daoEspaco;
    }

    public ArrayList<parceiro> getListarparceiros() {
        return this.listarparceiros;
    }

    public void setListarparceiros(ArrayList<parceiro> listarparceiros) {
        this.listarparceiros = listarparceiros;
    }

    public ArrayList<espaco> getListarespacos() {
        return this.listarespacos;
    }

    public void setListarespacos(ArrayList<espaco> listarespacos) {
        this.listarespacos = listarespacos;
    }
    

    // GETTERS AND SETTERS END 🎈

    public void carregarContrato(int idcontrato) {
        contrato = daocontrato.carregarContratoEspecifio(idcontrato);
        if(contrato == null) {
            JOptionPane.showMessageDialog(null, "o contrato nao foi carregado\n" + idcontrato,
                "erro 445j", JOptionPane.INFORMATION_MESSAGE);

        }
    }
    public boolean salvar() {
        if (this.editarRegistro == true) {
            return daocontrato.atualizarContrato(contrato);
        } else {
            return daocontrato.salvar(contrato);
        }
    }
    public boolean deletar(contrato contr) {
        return daocontrato.removerContrato(contr);
    }

    public void carregarContratos() {
        listaContratos = daocontrato.carregarTodosContratos();
    }



    public DefaultTableModel gerartDefaultTableModel() {
        carregarContratos();
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("id");
        model.addColumn("duração");
        model.addColumn("valor de entrada");
        model.addColumn("valor do aluguel");
        model.addColumn("porcemtagem de taxa");
        model.addColumn("data de inicio");
        model.addColumn("estado do contrato");
        model.addColumn("nome do parceiro");
        model.addColumn("espaço alugado");

        for (int i = 0; i<listaContratos.size(); i++) {
            contrato contr = listaContratos.get(i);
            Object[] dados = {
                contr.getId(),
                contr.getDuracao(),
                contr.getValor_entrada(),
                contr.getValor_alugel(),
                contr.getPorcemtagem_taxa(),
                contr.getData_inicio(),
                contr.estadoContrato(),
                contr.getParceiro().getNome_fantasia(),
                contr.getEspaco().getId(),
            };
            model.addRow(dados);
        }
        return model;
    }

    //--------------------------------------------------

    public String[] exibirParceiros() {
        String[] parceiros = new String[listarparceiros.size()];
        for (int i = 0; i<listarparceiros.size(); i++) {
            parceiros[i] = listarparceiros.get(i).getNome_fantasia();
        }
        return parceiros;
    } 

    public Integer[] exibirEspacos() {
        Integer[] espacos = new Integer[listarespacos.size()];
        for(int i = 0; i<listarespacos.size(); i++) {
            espacos[i] = listarespacos.get(i).getId();
        }
        return espacos;
    }


    
}
