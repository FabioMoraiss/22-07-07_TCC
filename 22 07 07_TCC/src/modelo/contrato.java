package modelo;

import java.util.Date;

public class contrato {
    private Integer id;
    private Date duracao;
    private Double valor_entrada;
    private Double valor_alugel;
    private Double porcemtagem_taxa;
    private Date data_inicio;
    private boolean ativo;
    private parceiro parceiro;
    private espaco espaco;


    public contrato() {
        
    }
    // GETTERS AND SETTERS START 🎈

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDuracao() {
        return this.duracao;
    }

    public void setDuracao(Date duracao) {
        this.duracao = duracao;
    }

    public Double getValor_entrada() {
        return this.valor_entrada;
    }

    public void setValor_entrada(Double valor_entrada) {
        this.valor_entrada = valor_entrada;
    }

    public Double getValor_alugel() {
        return this.valor_alugel;
    }

    public void setValor_alugel(Double valor_alugel) {
        this.valor_alugel = valor_alugel;
    }

    public Double getPorcemtagem_taxa() {
        return this.porcemtagem_taxa;
    }

    public void setPorcemtagem_taxa(Double porcemtagem_taxa) {
        this.porcemtagem_taxa = porcemtagem_taxa;
    }

    public Date getData_inicio() {
        return this.data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public parceiro getParceiro() {
        return this.parceiro;
    }

    public void setParceiro(parceiro parceiro) {
        this.parceiro = parceiro;
    }

    public espaco getEspaco() {
        return this.espaco;
    }

    public void setEspaco(espaco espaco) {
        this.espaco = espaco;
    }
// GETTERS AND SETTERS AND 🎈

    public String estadoContrato() {
        if (ativo) {
            return "ativo";
        } else {
            return "inativo";
        }
    }
    
}
