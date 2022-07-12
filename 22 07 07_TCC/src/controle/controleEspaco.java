package controle;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import JFrameVisual.listaEspacos;
import JFrameVisual.registrarEspaco;
import modelo.espaco;
import modelo.folha_aluguel;
import persistencia.daoEspaco;
import persistencia.daoLocalizacao;

public class controleEspaco {
    private espaco espaco;
    private ArrayList<espaco> listaEspacos;
    private daoEspaco daoEspaco;
    private boolean editarRegistro = false ;
    private daoLocalizacao daoLocalizacao;

    public controleEspaco() {
        espaco = new espaco();
        listaEspacos = new ArrayList<>();
        daoEspaco = new daoEspaco();
        daoLocalizacao = new daoLocalizacao();
    }

    // GETTERS AND SETTERS START 🎈

    public espaco getEspaco() {
        return this.espaco;
    }

    public void setEspaco(espaco espaco) {
        this.espaco = espaco;
    }

    public ArrayList<espaco> getListaEspacos() {
        return this.listaEspacos;
    }

    public void setListaEspacos(ArrayList<espaco> listaEspacos) {
        this.listaEspacos = listaEspacos;
    }

    public daoEspaco getDaoEspaco() {
        return this.daoEspaco;
    }

    public void setDaoEspaco(daoEspaco daoEspaco) {
        this.daoEspaco = daoEspaco;
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

    public daoLocalizacao getDaoLocalizacao() {
        return this.daoLocalizacao;
    }

    public void setDaoLocalizacao(daoLocalizacao daoLocalizacao) {
        this.daoLocalizacao = daoLocalizacao;
    }
// GETTERS AND SETTERS END 🎈

    public boolean salvar() {
        if(this.editarRegistro == true) {
            return daoEspaco.atualizarEspaco(espaco);
        } else {
            return daoEspaco.registrarEspaco(espaco);
        }
    }

    public void carregarEspaco(int idespaco) {
        espaco = daoEspaco.carregarEspacoEspecifico(idespaco);
    }

    public boolean deletar(espaco idespaco) {
        return daoEspaco.removerEspaco(idespaco);
    }

    public void carregarEspacos() {
        listaEspacos = daoEspaco.carregarTodosEspacos();
    }

    public DefaultTableModel gerarTablemodelEspaco() {
        carregarEspacos();
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("metros_quadrados");
        model.addColumn("esta_alugado");
        model.addColumn("kiosque_loja");
        model.addColumn("id_localização");

        for (int i = 0; i <listaEspacos.size(); i++) {
            espaco esp = listaEspacos.get(i);
            Object[] dados = {
                esp.getId(),
                esp.getMetros_quadrados(),
                esp.alugado_view(),
                esp.kioque_view(),
                esp.getLocalizacao().getId(),
            };
            model.addRow(dados);
        }
        return model;
    }

    
    
}
