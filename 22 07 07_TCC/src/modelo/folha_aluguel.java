package modelo;

import java.util.Date;

public class folha_aluguel {
    private Integer id;
    private Double valor;
    private boolean foi_pago;
    private String descricao;
    private Integer numero_parcela;
    private Date data_vencimento;
    private contrato contrato;
    
    // GETTERS AND SETTERS START 🎈

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValor() {
        return this.valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isFoi_pago() {
        return this.foi_pago;
    }

    public boolean getFoi_pago() {
        return this.foi_pago;
    }

    public void setFoi_pago(boolean foi_pago) {
        this.foi_pago = foi_pago;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNumero_parcela() {
        return this.numero_parcela;
    }

    public void setNumero_parcela(Integer numero_parcela) {
        this.numero_parcela = numero_parcela;
    }

    public Date getData_vencimento() {
        return this.data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }

    public contrato getContrato() {
        return this.contrato;
    }

    public void setContrato(contrato contrato) {
        this.contrato = contrato;
    }
// GETTERS AND SETTERS AND 🎈

public String estadoDaDivida() {
    if(foi_pago) {
        return "quitada" ;
    } else {
        return "não paga" ;
    }
}
}
