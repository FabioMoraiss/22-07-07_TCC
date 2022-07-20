package persistencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.print.DocFlavor.STRING;
import javax.swing.JOptionPane;
import javax.xml.transform.SourceLocator;
import java.sql.PreparedStatement;

import modelo.folha_aluguel;
import modelo.contrato;

public class daoContrato extends DAO {
    private daoParceiro daoParceiro;
    private daoEspaco daoEspaco;
    private daoFolha_aluguel daoFolha_aluguel;

    public daoContrato() {
        daoParceiro = new daoParceiro();
        daoEspaco = new daoEspaco();
        //daoFolha_aluguel = new daoFolha_aluguel();
    }


    public ArrayList<contrato> carregarTodosContratos() {
        ArrayList<contrato> contratos = new ArrayList<>();
        try {
            String sql = "select * from contrato";
            ResultSet rs = consultaSQL(sql);

            while(rs.next()) {
                contrato contrato = new contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setDuracao(rs.getDate("duracao"));
                contrato.setValor_entrada(rs.getDouble("valor_entrada"));
                contrato.setValor_alugel(rs.getDouble("valor_aluguel"));
                contrato.setPorcemtagem_taxa(rs.getDouble("porcemtagem_taxa"));
                contrato.setData_inicio(rs.getDate("data_inicio"));
                contrato.setAtivo(rs.getBoolean("ativo"));

                Integer idParceiro = rs.getInt("id_parceiro");
                if (idParceiro != null && idParceiro > 0) {
                    contrato.setParceiro(daoParceiro.carregarParceiroEspecifico(idParceiro));
                }

                Integer idEspaco = rs.getInt("id_espaco") ;
                if (idEspaco != null && idEspaco > 0) {
                    contrato.setEspaco(daoEspaco.carregarEspacoEspecifico(idEspaco));
                }

                contratos.add(contrato);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar todos os contratos\n" + ex.getMessage()),
             "erro 66gg", JOptionPane.ERROR_MESSAGE);
        }
        return contratos;

    }
    public contrato carregarContratoEspecifio(int id ) {
        contrato contrato = null;
        try {
            String sql = "select * from contrato where id = " +id;
            ResultSet rs = consultaSQL(sql);
            
            if(rs.next()) {
                contrato = new contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setDuracao(rs.getDate("duracao"));
                contrato.setValor_entrada(rs.getDouble("valor_entrada"));
                contrato.setValor_alugel(rs.getDouble("valor_aluguel"));
                contrato.setPorcemtagem_taxa(rs.getDouble("porcemtagem_taxa"));
                contrato.setData_inicio(rs.getDate("data_inicio"));
                contrato.setAtivo(rs.getBoolean("ativo"));

                Integer idParceiro = rs.getInt("id_parceiro");
                if (idParceiro != null && idParceiro > 0) {
                    contrato.setParceiro(daoParceiro.carregarParceiroEspecifico(idParceiro));
                }

                Integer idEspaco = rs.getInt("id_espaco") ;
                if (idEspaco != null && idEspaco > 0) {
                    contrato.setEspaco(daoEspaco.carregarEspacoEspecifico(idEspaco));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao carregar contrato especifico\n" + ex.getMessage()),
             "erro 77hh", JOptionPane.ERROR_MESSAGE);
        }
        return contrato;
    }

    public boolean salvar(contrato contr) {
        try {
            String sql = "INSERT INTO public.contrato(\n" +
                "id, duracao, valor_entrada, valor_aluguel, porcemtagem_taxa, data_inicio, ativo, id_parceiro, id_espaco)\n"+
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement ps = criaPreparedStatement(sql);

                contr.setId(gerarProximoID("contrato"));
                ps.setInt(1, contr.getId());
                ps.setDate(2, new java.sql.Date(contr.getDuracao().getTime()));
                ps.setDouble(3, contr.getValor_entrada());
                ps.setDouble(4, contr.getValor_alugel());
                ps.setDouble(5, contr.getPorcemtagem_taxa());
                ps.setDate(6, new java.sql.Date(contr.getData_inicio().getTime()));

                ps.setBoolean(7, contr.getAtivo());

                if(contr.getParceiro() != null) {
                    if (contr.getParceiro().getId() == null || contr.getParceiro().getId() == 0) {
                        daoParceiro.registrarParceiro(contr.getParceiro());
                    }
                    ps.setInt(8, contr.getParceiro().getId());
                } else {
                    ps.setObject(8, null);
                }

                if(contr.getEspaco() != null) {
                    if (contr.getEspaco().getId() == null || contr.getEspaco().getId() == 0) {
                        daoEspaco.registrarEspaco(contr.getEspaco()) ;
                    } else {
                        daoEspaco.atualizarEspaco(contr.getEspaco());
                    }
                    ps.setObject(9, contr.getEspaco().getId());
                } else {
                    ps.setObject(9, null);
                }

                ps.execute();

                salvarGuiaAutomatico(contr);
                return true;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao salvar contrato\n" + ex.getMessage()),
             "erro 88hh", JOptionPane.ERROR_MESSAGE);

             return false ;
        }
    }

    private void salvarGuiaAutomatico(contrato contr) {
        daoFolha_aluguel = new daoFolha_aluguel();
        folha_aluguel fol = new folha_aluguel();
        Calendar cal = Calendar.getInstance();
        cal.setTime(contr.getData_inicio());
        cal.add(Calendar.MONTH, 1);

        fol.setContrato(contr);
        fol.setData_vencimento(cal.getTime());
        fol.setValor(contr.getValor_entrada());
        fol.setDescricao("primeira guia automatica");
        fol.setFoi_pago(false);
        fol.setNumero_parcela(0);

        daoFolha_aluguel.registarDivida(fol);
    }

    public boolean removerContrato(contrato contr) {
        try {
            String sql = "DELETE FROM public.contrato\n" +
            "WHERE id = " + contr.getId() +
            "; ";

            executeDeleteSQL(sql);
            return true ;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,("falha ao deletar contrato\n" + ex.getMessage()),
             "erro 22ii", JOptionPane.ERROR_MESSAGE);
             return false ;
        }
    }

    public boolean finalizarContrato(contrato contr) {
        try {
            String sql = "UPDATE public.contrato\n" +
            "set ativo = false\n" +
            "where id = " +contr.getId();

            executeDeleteSQL(sql);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao finalizar contrato" + ex.getMessage()),
             "erro 2244pg", JOptionPane.ERROR_MESSAGE);

             return false;
        }
    }
    public boolean atualizarContrato(contrato contr) {
        try {
            String sql = "UPDATE public.contrato\n" +
            "SET duracao=?, valor_entrada=?, valor_aluguel=?, porcemtagem_taxa=?, data_inicio=?, ativo=?, id_parceiro=?, id_espaco=?\n" +
            "WHERE id = " + contr.getId();

            PreparedStatement ps = criaPreparedStatement(sql);

            ps.setDate(1, new java.sql.Date(contr.getDuracao().getTime()));
            ps.setDouble(2, contr.getValor_entrada());
            ps.setDouble(3, contr.getValor_alugel());
            ps.setDouble(4, contr.getPorcemtagem_taxa());
            ps.setDate(5, new java.sql.Date(contr.getData_inicio().getTime()));
            ps.setBoolean(6, contr.getAtivo());

            
            /*if (contr.getParceiro().getId() == null || contr.getParceiro().getId() == 0) {
                ps.setInt(6, contr.getParceiro().getId());
            }
            if (contr.getEspaco().getId() == null || contr.getEspaco().getId() == 0) {
                ps.setInt(7, contr.getEspaco().getId());
            }*/

            if(contr.getParceiro() != null) {
                if(contr.getParceiro().getId() == null) {
                    daoParceiro.registrarParceiro(contr.getParceiro());
                } else {
                    daoParceiro.atualizarParceiro(contr.getParceiro());
                }
                ps.setInt(7, contr.getParceiro().getId());
            } else {
                ps.setObject(7, null);
            }

            if(contr.getEspaco() != null) {
                if(contr.getEspaco().getId() == null) {
                    daoEspaco.registrarEspaco(contr.getEspaco());
                } else {
                    daoEspaco.atualizarEspaco(contr.getEspaco());
                }
                ps.setInt(8, contr.getEspaco().getId());
            } else {
                ps.setObject(8, null);
            }

            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ("falha ao atualizar contrato" + ex.getMessage()),
             "erro 22jj", JOptionPane.ERROR_MESSAGE);

             return false ;
        }
    }


    
}
