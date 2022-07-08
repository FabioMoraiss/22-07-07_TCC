package persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
}
