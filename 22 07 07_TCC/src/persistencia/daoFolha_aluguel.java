package persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.transform.SourceLocator;

import modelo.folha_aluguel;
import persistencia.daoContrato;

public class daoFolha_aluguel extends DAO{
    private daoContrato daocontrato;

    public daoFolha_aluguel() {
        daocontrato = new daoContrato();
    }

    public ArrayList<folha_aluguel> carregarTodasDividas() {
        ArrayList<folha_aluguel> guias = new ArrayList<>();
        try {
            String sql = "select * from public.folha_alugel\n" +
            "left join contrato as contr on id_contrato = contr.id\n" +
            "order by folha_alugel.id;" ;

            ResultSet rs = consultaSQL(sql);

            while(rs.next()) {
                folha_aluguel folha = new folha_aluguel();

                folha.setId(rs.getInt("id"));
                folha.setValor(rs.getDouble("valor"));
                folha.setFoi_pago(rs.getBoolean("foi_pago"));
                folha.setDescricao(rs.getString("descricao"));
                folha.setNumero_parcela(rs.getInt("numero_parcela"));
                folha.setData_vencimento(rs.getDate("data_vencimento"));

                Integer idContrato = rs.getInt("id_contrato");
                if(idContrato != null && idContrato> 0) {
                    folha.setContrato(daocontrato.carregarContratoEspecifio(idContrato));
                }

                /*if(rs.getObject("id_contrato", Integer.class) != null) {
                    folha.getContrato().setId(rs.getInt("id_contrato"));
                    folha.getContrato().getParceiro().setNome_fantasia(rs.getString("nome_fantasia"));
                    folha.getContrato().getEspaco().setId(rs.getInt("id_espaco")); */

                    /*folha.getContrato().setDuracao(rs.getDate("duração"));
                    folha.getContrato().setValor_entrada(rs.getDouble("valor_entrada"));
                    folha.getContrato().setValor_alugel(rs.getDouble("valor_aluguel"));
                    folha.getContrato().setPorcemtagem_taxa(rs.getDouble("porcemtagem_taxa"));
                    folha.getContrato().setData_inicio(rs.getDate("data_inicio"));
                    folha.getContrato().setAtivo(rs.getBoolean("ativo")); */

                    guias.add(folha);

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar folha de aluguel\n" + ex.getMessage()),
             "44mm", JOptionPane.ERROR_MESSAGE);
        }
        return guias ;
    }
    public boolean registarDivida(folha_aluguel fol) {
        try {
            String sql = "INSERT INTO public.folha_alugel(\n" +
                "id, valor, foi_pago, descricao, numero_parcela, data_vencimento, id_contrato)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement ps = criaPreparedStatement(sql);

                fol.setId(gerarProximoID("folha_alugel"));
                ps.setInt(1, fol.getId());
                ps.setDouble(2, fol.getValor());
                ps.setBoolean(3, fol.getFoi_pago());
                ps.setString(4, fol.getDescricao());
                ps.setInt(5, fol.getNumero_parcela());
                ps.setDate(6, new java.sql.Date(fol.getData_vencimento().getTime()));
                //ps.setInt(7, fol.getContrato().getId());

                if(fol.getContrato() != null) {
                    if(fol.getContrato().getId() == null || fol.getContrato().getId() == 0) {
                        daocontrato.salvar(fol.getContrato());
                    }
                    ps.setInt(7, fol.getContrato().getId());
                } else {
                    ps.setObject(7, null);
                }

                ps.executeUpdate();
                return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao registrar divida\n" + ex.getMessage()),
             "erro 22nn", JOptionPane.ERROR_MESSAGE);

             return false;
        }

    }
    public boolean atualizarDivida(folha_aluguel fol) {
        try {
            String sql = "UPDATE public.folha_alugel\n" +
            "SET valor=?, foi_pago=?, descricao=?, numero_parcela=?, data_vencimento=?, id_contrato=?\n" +
            "WHERE id = " + fol.getId();

            PreparedStatement ps = criaPreparedStatement(sql);

                ps.setDouble(1, fol.getValor());
                ps.setBoolean(2, fol.getFoi_pago());
                ps.setString(3, fol.getDescricao());
                ps.setInt(4, fol.getNumero_parcela());
                ps.setDate(5, new java.sql.Date(fol.getData_vencimento().getTime()));
                //ps.setInt(6, fol.getContrato().getId());

               if(fol.getContrato() != null) {
                    if(fol.getContrato().getId() == null) {
                    daocontrato.salvar(fol.getContrato());
                    } else {
                    daocontrato.atualizarContrato(fol.getContrato());
                 }
                ps.setInt(6, fol.getContrato().getId());
               } else {
                ps.setObject(6, null);
               }

                ps.executeUpdate();
                return true;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao atualizar divida\n" + ex.getMessage()),
             "erro 55oo", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }
    public folha_aluguel carregarDividaEspecifica(int id) {
        folha_aluguel dividaEspecifica = null;
        try {
            String sql = "select * from public.folha_alugel\n" +
            "where id = " +id;

            ResultSet rs = consultaSQL(sql);

            while(rs.next()) {
                dividaEspecifica = new folha_aluguel();

                dividaEspecifica.setId(id);
                dividaEspecifica.setValor(rs.getDouble("valor"));
                dividaEspecifica.setFoi_pago(rs.getBoolean("foi_pago"));
                dividaEspecifica.setDescricao(rs.getString("descricao"));
                dividaEspecifica.setNumero_parcela(rs.getInt("numero_parcela"));
                dividaEspecifica.setData_vencimento(rs.getDate("data_vencimento"));

                /*if(rs.getObject("id_contrato", Integer.class) != null) {
                    dividaEspecifica.getContrato().setId(rs.getInt("id_contrato"));
                    dividaEspecifica.getContrato().getParceiro().setNome_fantasia(rs.getString("nome_fantasia"));
                    dividaEspecifica.getContrato().getEspaco().setId(rs.getInt("id_espaco"));
            } */

            Integer idContrato = rs.getInt("id_contrato");
            if(idContrato != null && idContrato >0) {
                dividaEspecifica.setContrato(daocontrato.carregarContratoEspecifio(idContrato));
            }

        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null,("falha ao carregar divida especifica\n" + ex.getMessage()),
             "66pp", JOptionPane.ERROR_MESSAGE);

    }
    return dividaEspecifica;
    }
    public boolean removerDivida(folha_aluguel fol) {
        try {
            String sql = "DELETE FROM public.folha_alugel\n" +
            "WHERE ID = " + fol.getId();

            executeDeleteSQL(sql);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao deletar divida\n" + ex.getMessage()),
             "erro 11qq", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }

    
}
