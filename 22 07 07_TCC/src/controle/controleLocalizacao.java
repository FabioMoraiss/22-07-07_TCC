package controle;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import JFrameVisual.listaLocais;
import JFrameVisual.registrarLocalizacao;
import modelo.localizacao;
import persistencia.daoLocalizacao;

public class controleLocalizacao {
    private ArrayList<localizacao> listaDeLocais;
    private localizacao localizacao;
    private daoLocalizacao daoLocalizacao;
    private boolean editarRegistro = false;

    public controleLocalizacao() {
        listaDeLocais = new ArrayList<>();
        localizacao = new localizacao();
        daoLocalizacao = new daoLocalizacao();
    }

    // GETTERS AND SETTERS START 🎈

    public ArrayList<localizacao> getListaDeLocais() {
        return this.listaDeLocais;
    }

    public void setListaDeLocais(ArrayList<localizacao> listaDeLocais) {
        this.listaDeLocais = listaDeLocais;
    }

    public localizacao getLocalizacao() {
        return this.localizacao;
    }

    public void setLocalizacao(localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public daoLocalizacao getDaoLocalizacao() {
        return this.daoLocalizacao;
    }

    public void setDaoLocalizacao(daoLocalizacao daoLocalizacao) {
        this.daoLocalizacao = daoLocalizacao;
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

public void carregarLocalizacao(int idlocal) {
    localizacao = daoLocalizacao.carregarLocalEspecifico(idlocal);
}

public boolean salvar() {
    if (this.editarRegistro == true) {
        return daoLocalizacao.atualizarLocal(localizacao);
    } else {
        return daoLocalizacao.registarLocal(localizacao);
    }
}
public boolean deletar(localizacao idlocal) {
    return daoLocalizacao.removerLocalizacao(idlocal);
}

public void carregarLocalizacoes() {
    listaDeLocais = daoLocalizacao.carregarTodosLocalizacao();
}

public DefaultTableModel gerartDefaultTableModel() {
    carregarLocalizacoes();
    DefaultTableModel model  = new DefaultTableModel();

    model.addColumn("id");
    model.addColumn("descrição");
    model.addColumn("andar");
    model.addColumn("região");
    model.addColumn("bloco");

    for (int i = 0; i<listaDeLocais.size(); i++) {
        localizacao loli = listaDeLocais.get(i);
        Object[] dados = {
            loli.getId(),
            loli.getDescricao(),
            loli.getAndar(),
            loli.getRegiao(),
            loli.getBloco(),
            
        };
        model.addRow(dados);
    }
    return model;
}

    
}
