package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import modelo.localizacao;

public class daoLocalizacao extends DAO{
    public ArrayList<localizacao> carregarTodosLocalizacao() {
        ArrayList<localizacao> locais = new ArrayList<>();
        try {
            String sql = "select * from localizacao";
            ResultSet rs = consultaSQL(sql);
            
            while(rs.next()) {
                localizacao local = new localizacao();
                local.setId(rs.getInt("id"));
                local.setDescricao(rs.getString("descricao"));
                local.setAndar(rs.getString("andar"));
                local.setRegiao(rs.getString("regiao"));
                local.setBloco(rs.getString("bloco"));

                locais.add(local);
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar todos os locais\n" + ex.getMessage()),
             "erro 9999", JOptionPane.ERROR_MESSAGE);

        }
        return locais;
    }
    public localizacao carregarLocalEspecifico(int id) {
        localizacao localEspecifico = null ;

        try {
            String sql = "select * from localizacao\n" +
            "where id = " +id;

            ResultSet rs = consultaSQL(sql);

            if(rs.next()) {
                localEspecifico = new localizacao();
                localEspecifico.setId(rs.getInt("id"));
                localEspecifico.setDescricao(rs.getString("descricao"));
                localEspecifico.setAndar(rs.getString("andar"));
                localEspecifico.setRegiao(rs.getString("regiao"));
                localEspecifico.setBloco(rs.getString("bloco"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar local especifico\n" + ex.getMessage()),
             "erro 11AA", JOptionPane.ERROR_MESSAGE);
        }
        return localEspecifico;
    }

    public boolean registarLocal(localizacao locali) {
        try {
            String sql ="INSERT INTO public.localizacao(\n" +
                "id, descricao, andar, regiao, bloco)\n" +
                "VALUES (?, ?, ?, ?, ?);";
            
            PreparedStatement ps = criaPreparedStatement(sql);

            locali.setId(gerarProximoID("localizacao"));
            ps.setInt(1, locali.getId());
            ps.setString(2, locali.getDescricao());
            ps.setString(3, locali.getAndar());
            ps.setString(4, locali.getRegiao());
            ps.setString(5, locali.getBloco());

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao salvar localizacao\n" + ex.getMessage()),
             "erro 22bb", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }
    public String comandoSqlDelete(localizacao locali) {
        String sql = "DELETE FROM public.localizacao\n" +
        "WHERE id = " + locali.getId();

        return sql;
    }
    public boolean removerLocalizacao(localizacao locali) {
        try {
            String sql = "DELETE FROM localizacao\n" +
            "WHERE id = " + locali.getId();

            executeDeleteSQL(sql);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao deletar localizacao\n" + ex.getMessage()),
             "erro 33cc", JOptionPane.ERROR_MESSAGE);
            
             return false;
        }
    }
    public boolean atualizarLocal(localizacao locali) {
        try {
            String sql = "UPDATE public.localizacao\n" +
            "descricao=?, andar=?, regiao=?, bloco=?\n" +
            "WHERE id = " +locali.getId();

            PreparedStatement ps = criaPreparedStatement(sql);

            ps.setString(1, locali.getDescricao());
            ps.setString(2, locali.getAndar());
            ps.setString(3, locali.getRegiao());
            ps.setString(4, locali.getBloco());

            ps.executeUpdate();

            return true;
        } catch (SQLException ex ) {
            JOptionPane.showMessageDialog(null, ("falha ao atualizar localizacao"+ ex.getMessage()),
             "erro 44dd", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }
    
}
