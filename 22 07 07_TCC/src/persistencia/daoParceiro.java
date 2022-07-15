package persistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.transform.SourceLocator;
import java.sql.PreparedStatement;

import modelo.parceiro;


public class daoParceiro extends DAO {
    
    public ArrayList<parceiro> carregarTodosParceiros() {
        ArrayList<parceiro> parceiros = new ArrayList<>();
        try {
            String sql ="select * from public.parceiro" ;
            ResultSet rs = consultaSQL(sql);
            while(rs.next()) {
                parceiro parceiro = new parceiro();
                parceiro.setId(rs.getInt("id"));
                parceiro.setNome_fantasia(rs.getString("nome_fantasia"));
                parceiro.setRazao_social(rs.getString("razao_social"));
                parceiro.setCnpj(rs.getDouble("cnpj"));
                parceiro.setTelefone(rs.getDouble("telefone"));
                parceiro.setEmail(rs.getString("email"));

                parceiros.add(parceiro);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar todos os parceiros\n" + ex.getMessage()),
             "erro 5555", JOptionPane.ERROR_MESSAGE);
        }
        return parceiros;
    }
     public boolean registrarParceiro(parceiro parce) {
        try {
            String sql = "INSERT INTO public.parceiro(\n" +
                "id, nome_fantasia, razao_social, cnpj, email, telefone)\n" +
                "VALUES (?, ?, ?, ?, ?, ?);";

                PreparedStatement ps = criaPreparedStatement(sql);

                parce.setId(gerarProximoID("parceiro"));
                ps.setInt(1, parce.getId());
                ps.setString(2, parce.getNome_fantasia());
                ps.setString(3, parce.getRazao_social());
                ps.setDouble(4, parce.getCnpj());
                ps.setString(5, parce.getEmail());
                ps.setDouble(6, parce.getTelefone());

                ps.execute();
                return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao registrar parceiro\n" + ex.getMessage()),
             "erro 6666", JOptionPane.ERROR_MESSAGE);
            
             return false;
        }
    } 
    public boolean atualizarParceiro(parceiro parce) {
        try {
            String sql = "UPDATE public.parceiro\n" +
            "SET nome_fantasia=?, razao_social=?, cnpj=?, email=?, telefone=?\n" +
            "WHERE id = " + parce.getId();

            PreparedStatement ps = criaPreparedStatement(sql);

            ps.setString(1, parce.getNome_fantasia());
            ps.setString(2, parce.getRazao_social());
            ps.setDouble(3, parce.getCnpj());
            ps.setString(4, parce.getEmail());
            ps.setDouble(5, parce.getTelefone());

            ps.execute();

            return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao EDITAR parceiro\n" + ex.getMessage()),
             "erro 7777", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }
    public parceiro carregarParceiroEspecifico(int id) {
        parceiro parceiroEspecifico = null ;
        try {
            String sql = "select * from public.parceiro\n" +
            "where id = " +id;

            ResultSet rs = consultaSQL(sql);

            if (rs.next()) {
                parceiroEspecifico = new parceiro();

                parceiroEspecifico.setId(rs.getInt("id"));
                parceiroEspecifico.setNome_fantasia(rs.getString("nome_fantasia"));
                parceiroEspecifico.setRazao_social(rs.getString("razao_social"));
                parceiroEspecifico.setCnpj(rs.getDouble("cnpj"));
                parceiroEspecifico.setTelefone(rs.getDouble("telefone"));
                parceiroEspecifico.setEmail(rs.getString("email"));

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,("falha ao carregar parceiro especifico\n" + ex.getMessage()),
             "erro 8888", JOptionPane.ERROR_MESSAGE);
        }
        return parceiroEspecifico;
    }
    public boolean removerParceiro(parceiro parce) {
        try {
            String sql = "DELETE FROM public.parceiro\n" +
            "WHERE id =" + parce.getId(); 

            executeDeleteSQL(sql);
            return true;
        } catch( SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao deletar parceiro\n" + ex.getMessage()),
            "erro 8888", JOptionPane.ERROR_MESSAGE);

            return false;
        }
    }
}
