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
    /* public boolean registrarParceiro(parceiro parce) {
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
                ps.setString(5, parce.ge);
        }
    } */
}
