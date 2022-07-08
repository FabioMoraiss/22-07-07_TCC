package persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class conexaobd {
    private static Connection conexao;

    public static void abrirConexao() {
        try {
            String baseDados = "shoping_aluguel_1";
            String usuario = "postgres";
            String senha = "postgres";
            String url ="jdbc:postgresql://localhost:5432/shoping_aluguel_1";

            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("conexao realizada ONII-CHAN 🧡");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "FALHA NA CONEXAO COM BANCO DE DADOS\n" + ex.getMessage(),
            "erro 1111", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "falha ao abir conexao com o banco de dados\n " + ex.getMessage(),
             "erro 2222", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static Connection getConexao() {
        if (conexao == null) {
            abrirConexao();
        }
        return conexao;
    }
}
