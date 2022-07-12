package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.login.CredentialException;
import javax.swing.JOptionPane;
import javax.xml.transform.SourceLocator;
import java.sql.PreparedStatement;

import modelo.espaco;
import persistencia.daoLocalizacao;


public class daoEspaco extends DAO {
    private daoLocalizacao daoLocalizacao;

    public daoEspaco() {
        daoLocalizacao = new daoLocalizacao();
    }

    public ArrayList<espaco> carregarTodosEspacos() {
        ArrayList<espaco> espacos = new ArrayList<>();

        try {
            String sql = "select * from public.espaco\n" +
            " left join localizacao as locali on id_localizacao = locali.id \n" +
            " order by espaco.id;";

            ResultSet rs = consultaSQL(sql);

            while(rs.next()) {
                espaco espaco = new espaco();

                espaco.setId(rs.getInt("id"));
                espaco.setMetros_quadrados(rs.getDouble("metros_quadrados"));
                espaco.setEstado_alugado(rs.getBoolean("estado_alugado"));
                espaco.setKiosque_loja(rs.getBoolean("kiosque_loja"));

                if(rs.getObject("id_localizacao", Integer.class) != null) {
                    espaco.getLocalizacao().setId(rs.getInt("id_localizacao"));
                    espaco.getLocalizacao().setDescricao(rs.getString("descricao"));
                    espaco.getLocalizacao().setAndar(rs.getInt("andar"));
                    espaco.getLocalizacao().setRegiao(rs.getString("regiao"));
                    espaco.getLocalizacao().setBloco(rs.getString("bloco"));
                }

                espacos.add(espaco);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar todos os espacos\n" + ex.getMessage()),
             "55ff", JOptionPane.ERROR_MESSAGE);
        }

        return espacos;
    }
    public boolean registrarEspaco(espaco espace) {
        try {
            String sql = "INSERT INTO public.espaco(\n" +
                "id, metros_quadrados, esta_alugado, kiosque_loja, id_localizacao)\n" +
                "VALUES (?, ?, ?, ?, ?);";

            PreparedStatement ps = criaPreparedStatement(sql);

            espace.setId(gerarProximoID("espaco"));
            ps.setInt(1, espace.getId());
            ps.setDouble(2, espace.getMetros_quadrados());
            ps.setBoolean(3, espace.getEstado_alugado());
            ps.setBoolean(4, espace.getKiosque_loja());

            if(espace.getLocalizacao() != null) {
                if(espace.getLocalizacao().getId() == null || espace.getLocalizacao().getId() == 0) {
                    daoLocalizacao.registarLocal(espace.getLocalizacao());
                }
                ps.setInt(5, espace.getLocalizacao().getId());
            } else {
                ps.setObject(5, null);
            }

            ps.execute();
            return true;
        } catch (SQLException ex) {
            try{ getConexao().rollback(); //CRTL+Z
            } catch (SQLException ex1) {
                System.out.println("falha ao realizar rollback");
            }

            JOptionPane.showMessageDialog(null, ("falha ao salvar espaço\n" + ex.getMessage()),
             "erro 55kk", JOptionPane.ERROR_MESSAGE);

             return false ;
        }
    }
    public boolean atualizarEspaco(espaco espace) {
        try{
            String sql ="UPDATE public.espaco\n" +
            "SET metros_quadrados=?, esta_alugado=?, kiosque_loja=?, id_localizacao=?\n" +
            "WHERE id = " +espace.getId();

            PreparedStatement ps = criaPreparedStatement(sql);

            ps.setDouble(1, espace.getMetros_quadrados());
            ps.setBoolean(2, espace.getEstado_alugado());
            ps.setBoolean(3, espace.getKiosque_loja());

            if(espace.getLocalizacao() != null) {
                if (espace.getLocalizacao().getId() == null) {
                    daoLocalizacao.registarLocal(espace.getLocalizacao());
                } else {
                    daoLocalizacao.atualizarLocal(espace.getLocalizacao());
                }
                ps.setInt(4, espace.getLocalizacao().getId());
            } else {
                ps.setObject(4, null);
            }

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao EDITAR espaco\n" + ex.getMessage()),
             "erro 44ll", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }

    public espaco carregarEspacoEspecifico(int id) {
        espaco espacoEspecifico = null;
        try {
            String sql = "select * from public.espaco " +
            "left join localizacao as local on id_localizacao = local.id" +
            " where espaco.id = " +id;

            ResultSet rs = consultaSQL(sql);

            if(rs.next()) {
                espacoEspecifico = new espaco();
                espacoEspecifico.setId(rs.getInt("id"));
                espacoEspecifico.setMetros_quadrados(rs.getDouble("metros_quadrados"));
                espacoEspecifico.setEstado_alugado(rs.getBoolean("esta_alugado"));
                espacoEspecifico.setKiosque_loja(rs.getBoolean("kiosque_loja"));

                Integer idLocalizacao = rs.getInt("id_localizacao") ;
                if(idLocalizacao != null && idLocalizacao >0) {
                    espacoEspecifico.setLocalizacao(daoLocalizacao.carregarLocalEspecifico(idLocalizacao));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,("falha ao carregar espaco especifico\n" + ex.getMessage()),
             "erro 77oo", JOptionPane.ERROR_MESSAGE);
        }
        return espacoEspecifico ;
    }

    public boolean removerEspaco(espaco espace) {
        try {
            String sql = "DELETE FROM public.espaco\n" +
            "WHERE id = " + espace.getId();

            executeDeleteSQL(sql);
            return true ;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao deleter esapaço\n" + ex.getMessage()),
             "erro 99pp", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }
    
}
