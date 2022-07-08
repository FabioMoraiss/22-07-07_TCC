package persistencia;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DAO {
    private Connection conexao;
    private Statement statement ;

    public Connection getConexao() {
        return this.conexao;
    }
    
    public DAO() {
        conexao = conexaobd.getConexao();
        try {
            statement = conexao.createStatement();
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "FALHA OA CRIAR STATEMENT\n" + ex.getMessage(),
             "erro 3333", JOptionPane.ERROR_MESSAGE);
        }
    }
    public ResultSet consultaSQL(String comandoSQL) throws SQLException {
        ResultSet rs = statement.executeQuery(comandoSQL);
        return rs;
    }
    public PreparedStatement criaPreparedStatement(String sql) throws SQLException {
        return conexao.prepareStatement(sql);
    }
    public void executeDeleteSQL(String sql) throws SQLException {
        Statement st = conexao.createStatement();
        st.executeUpdate(sql);
        st.close();
    }
    public int gerarProximoID(String tabela) {
        int id = -1 ;
        try {
            ResultSet rs = consultaSQL("select max(id) from " + tabela);
            if(rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "falha ao pegar o ID maximo" + ex.getMessage(),
             "erro 4444", JOptionPane.ERROR_MESSAGE);

        }
        return -1;
    }
}
